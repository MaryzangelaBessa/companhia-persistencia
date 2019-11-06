import daos.DepartamentoDAO;
import daos.jpa.DepartamentoJPADAO;
import models.Departamento;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static Scanner scanner;
    private static DepartamentoDAO dDAO = new DepartamentoJPADAO();

    public static void main(String[] args) {

        boolean run = true;
        scanner = new Scanner(System.in);

        while (run) {
            System.out.println("\n----------MENU----------");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Inserir Departamento");
            System.out.println("2 - Listar Departamentos");
            System.out.println("3 - Cadastrar Projetos");
            System.out.println("4 - Cadastrar Funcionários");
            System.out.println("5 - Deletar Departamento");
            System.out.println("0 - Sair");
            System.out.println("---------------------------");
            System.out.println(">>> ");

            int id = -1;
            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    scanner.nextLine();
                    inserirDepartamento();
                    break;
                case 2:
                    scanner.nextLine();
                    listarDepartamentos();
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Cadastrar Projetos");

                    break;
                case 4:
                    scanner.nextLine();
                    System.out.println("Cadastrar Funcionários");
                    break;
                case 5:
                    scanner.nextLine();
                    deletarDepartamento();
                    break;
                case 0:
                    run = false;
                    System.out.println("GoodBye!");
                    return;
                default:
                    System.out.println("Escolha Invalida!");
                    break;
            }
        }
    }

    public static void inserirDepartamento() {
        String nomeDepartamento = null;
        try {
            dDAO.beginTransaction();
            System.out.println("Digite o nome do departamento a ser criado: ");
            nomeDepartamento = scanner.nextLine();
            dDAO.save(new Departamento(nomeDepartamento));
            dDAO.commit();
        } catch (IllegalStateException | PersistenceException e) {
            dDAO.rollback();
            e.printStackTrace();
        } finally {
            dDAO.close();
        }
    }

    public static void listarDepartamentos(){
        List<Departamento> departamentos = dDAO.findAll();
        dDAO.close();
        System.out.println("\n\n" + "Lista de Departamentos");
        for (Departamento departamento : departamentos) {
            System.out.println(departamento);
        }
    }

    public static void cadastrarProjetos(){


    }

    public static void deletarDepartamento(){
        Long numDepartamento = null;
        try {
            dDAO.beginTransaction();
            System.out.println("Digite o número do departamento a ser excluído: ");
            numDepartamento = Long.parseLong(scanner.nextLine());
            List<Departamento> departamentos = dDAO.findAll();
            if (numDepartamento != null) {
                for (Departamento departamento : departamentos) {
                    if (departamento.getNumero().equals(numDepartamento)) {
                        dDAO.delete(departamento);
                    }
                }
            }
            dDAO.commit();
        } catch (IllegalStateException | PersistenceException e) {
            dDAO.rollback();
            e.printStackTrace();
        } finally {
            dDAO.close();
        }
    }
}