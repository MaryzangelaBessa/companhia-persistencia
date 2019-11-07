import daos.DepartamentoDAO;
import daos.FuncionarioDAO;
import daos.ProjetoDAO;
import daos.jpa.DepartamentoJPADAO;
import daos.jpa.FuncionarioJPADAO;
import daos.jpa.ProjetoJPADAO;
import models.Departamento;
import models.Funcionario;
import models.Projeto;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static Scanner scanner;

    private static DepartamentoDAO dDAO = new DepartamentoJPADAO();
    private static ProjetoDAO pDAO = new ProjetoJPADAO();
    private static FuncionarioDAO fDAO = new FuncionarioJPADAO();

    private static Long numDepartamento = null;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n----------MENU----------");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Inserir Departamento"); //ok
            System.out.println("2 - Listar Departamentos"); //ok
            System.out.println("3 - Listar Projetos"); //ok
            System.out.println("4 - Listar Funcionários"); //ok
            System.out.println("5 - Cadastrar Projetos"); //ok
            System.out.println("6 - Cadastrar Funcionários"); //Todo: gOoodinho
            System.out.println("7 - Deletar Departamento"); //ok
            System.out.println("0 - Sair"); //ok
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
                    listarProjetos();
                    scanner.nextLine();
                    break;
                case 4:
                    scanner.nextLine();
                    listarFuncionarios();
                    break;
                case 5:
                    scanner.nextLine();
                    cadastrarProjetos();
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.println("Cadastrar Funcionários");
                    break;
                case 7:
                    scanner.nextLine();
                    deletarDepartamento();
                    break;
                case 0:
                    System.out.println("GoodBye!");
                    return;
                default:
                    System.out.println("Escolha Invalida!");
                    break;
            }
        }
    }

    private static void inserirDepartamento() {
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

    private static void listarDepartamentos(){
        List<Departamento> departamentos = dDAO.findAll();
        dDAO.close();
        System.out.println("\n\n" + "Lista de Departamentos");
        for (Departamento departamento : departamentos) {
            System.out.println(departamento);
        }
    }

    private static void listarProjetos(){
        List<Projeto> projetos = pDAO.findAll();
        pDAO.close();
        System.out.println("\n\n" + "Lista de Projetos");
        for (Projeto projeto : projetos) {
            System.out.println(projeto);
        }
    }

    private static void listarFuncionarios(){
        List<Funcionario> funcionarios = fDAO.findAll();
        fDAO.close();
        System.out.println("\n\n" + "Lista de Funcionários");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    private static void cadastrarProjetos(){
        numDepartamento = null;
        String nomeProjeto = null;
        int horasDuracao;

        listarDepartamentos();

        try {
            dDAO.beginTransaction();

            System.out.println("Digite o número do departamento que o projeto pertencerá: ");
            numDepartamento = Long.parseLong(scanner.nextLine());
            System.out.println("Digite o nome do projeto que será criado: ");
            nomeProjeto = scanner.nextLine();
            System.out.println("Digite a duração (em horas inteiras) do projeto que será criado: ");
            horasDuracao = Integer.parseInt(scanner.nextLine());

            List<Departamento> departamentos = dDAO.findAll();

            if (numDepartamento != null) {
                for (Departamento departamento : departamentos) {
                    if (departamento.getNumero().equals(numDepartamento)) {
                        Projeto projeto = new Projeto(nomeProjeto, horasDuracao, departamento);
                        pDAO.save(projeto);
                        pDAO.commit();
                        departamento.getProjetos().add(projeto);
                        dDAO.save(departamento);
                        dDAO.commit();
                        break;
                    }
                }
            }

        }catch (IllegalStateException | PersistenceException e) {
            dDAO.rollback();
            pDAO.rollback();
            e.printStackTrace();
        } finally {
            dDAO.close();
            pDAO.close();
        }
    }

    public static void cadastrarFuncionarios(){

    }



    private static void deletarDepartamento(){
        numDepartamento = null;
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