import actions.OptionsDepartamento;
import actions.OptionsFuncionarios;
import actions.OptionsProjetos;
import daos.*;
import daos.jpa.*;
import models.*;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static Scanner scanner;

    private static DepartamentoDAO dDAO = new DepartamentoJPADAO();
    private static FuncionarioDAO fDAO = new FuncionarioJPADAO();
    private static DependenteDAO depDAO = new DependenteJPADAO();

    private static OptionsDepartamento od = new OptionsDepartamento();
    private static OptionsProjetos op = new OptionsProjetos();
    private static OptionsFuncionarios of = new OptionsFuncionarios();

    private static Long numDepartamento = null;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n----------MENU----------");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Departamento");
            System.out.println("2 - Listar Departamentos");
            System.out.println("3 - Deletar Departamento");
            System.out.println("4 - Cadastrar Projeto");
            System.out.println("5 - Listar Projetos");
            System.out.println("6 - Deletar Projeto");
            System.out.println("7 - Cadastrar Funcionário");
            System.out.println("8 - Listar Funcionários");
            System.out.println("9 - Deletar Funcionário");
            System.out.println("10 - Cadastrar Dependente");
            System.out.println("11 - Listar Dependentes");
            System.out.println("12 - Deletar Dependente");
            System.out.println("0 - Sair");
            System.out.println("---------------------------");
            System.out.println(">>> ");

            int id = -1;
            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    scanner.nextLine();
                    od.cadastrarDepartamento();
                    break;

                case 2:
                    scanner.nextLine();
                    od.listarDepartamentos();
                    break;

                case 3:
                    scanner.nextLine();
                    od.listarDepartamentos();
                    od.deletarDepartamento();
                    break;

                case 4:
                    scanner.nextLine();
                    od.listarDepartamentos();
                    op.cadastrarProjeto();
                    break;

                case 5:
                    scanner.nextLine();
                    op.listarProjetos();
                    break;

                case 6:
                    scanner.nextLine();
                    op.listarProjetos();
                    op.deletarProjeto();
                    break;

                case 7:
                    scanner.nextLine();
                    od.listarDepartamentos();
                    of.cadastrarFuncionario();
                    break;
                case 8:
                    scanner.nextLine();
                    of.listarFuncionarios();
                    break;

                case 9:
                    scanner.nextLine();
                    of.listarFuncionarios();
                    of.deletarFuncionario();
                    break;

                case 10:
                    scanner.nextLine();
                    of.listarFuncionarios();
                    //odep.cadastrarDependente();
                    break;

                case 11:
                    scanner.nextLine();
                    //odep.listarDependentes();
                    break;

                case 12:
                    scanner.nextLine();
                    //odep.listarDependentes();
                    //odep.deletarDependente();
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

    private static void listarDependentes() {
        List<Dependente> dependentes = depDAO.findAll();
        depDAO.close();
        System.out.println("\n\n" + "Lista de Dependentes");
        for (Dependente dependente : dependentes) {
            System.out.println(dependente);
        }
    }


    private static void cadastrarDependentes(){
        Long idFuncionario = null;
        String nomeDependente = null;
        String sexoDependente = null;
        String dataNascDependente = null;

        List<Funcionario> funcionarios = fDAO.findAll();
        fDAO.close();
        System.out.println("\n\n" + "Lista de Funcionário");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }

        try {
            dDAO.beginTransaction();

            System.out.println("Digite o id do funcionário que o dependente pertencerá: ");
            idFuncionario = Long.parseLong(scanner.nextLine());
            System.out.println("Digite o nome do dependente que será cadastrado: ");
            nomeDependente = scanner.nextLine();
            System.out.println("Digite o sexo do dependente que será cadastrado: ");
            sexoDependente = scanner.nextLine();
            System.out.println("Digite a data de nascimento do dependente que será cadastrado: ");
            dataNascDependente = scanner.nextLine();

            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getId().equals(idFuncionario)) {
                    Dependente dependente = new Dependente(nomeDependente, sexoDependente, dataNascDependente, funcionario);
                    depDAO.save(dependente);
                    depDAO.commit();
                    funcionario.getDependentes().add(dependente);
                    fDAO.save(funcionario);
                    fDAO.commit();
                    break;
                }
            }

        } catch (IllegalStateException | PersistenceException e) {
            depDAO.rollback();
            fDAO.rollback();
            e.printStackTrace();
        } finally {
            depDAO.close();
            fDAO.close();
        }
    }
}