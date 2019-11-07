import daos.DepartamentoDAO;
import daos.DependenteDAO;
import daos.FuncionarioDAO;
import daos.ProjetoDAO;
import daos.jpa.DepartamentoJPADAO;
import daos.jpa.DependenteJPADAO;
import daos.jpa.FuncionarioJPADAO;
import daos.jpa.ProjetoJPADAO;
import models.Departamento;
import models.Dependente;
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
    private static DependenteDAO depDAO = new DependenteJPADAO();

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
            System.out.println("6 - Cadastrar Funcionários"); // ok ;)
            System.out.println("7 - Cadastrar Dependentes");
            System.out.println("8 - Deletar Departamento"); //ok
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
                    cadastrarFuncionarios();
                    break;
                case 7:
                    scanner.nextLine();
                    cadastrarDependentes();
                    break;
                case 8:
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
            System.out.println("Digite o nome do departamento a ser cadastrado: ");
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

    private static void listarDepartamentos() {
        List<Departamento> departamentos = dDAO.findAll();
        dDAO.close();
        System.out.println("\n\n" + "Lista de Departamentos");
        for (Departamento departamento : departamentos) {
            System.out.println(departamento);
        }
    }

    private static void listarProjetos() {
        List<Projeto> projetos = pDAO.findAll();
        pDAO.close();
        System.out.println("\n\n" + "Lista de Projetos");
        for (Projeto projeto : projetos) {
            System.out.println(projeto);
        }
    }

    private static void listarFuncionarios() {
        List<Funcionario> funcionarios = fDAO.findAll();
        fDAO.close();
        System.out.println("\n\n" + "Lista de Funcionários");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    private static void cadastrarProjetos() {
        numDepartamento = null;
        String nomeProjeto = null;
        int horasDuracao;

        listarDepartamentos();

        try {
            dDAO.beginTransaction();

            System.out.println("Digite o número do departamento que o projeto pertencerá: ");
            numDepartamento = Long.parseLong(scanner.nextLine());
            System.out.println("Digite o nome do projeto que será cadastrado: ");
            nomeProjeto = scanner.nextLine();
            System.out.println("Digite a duração (em horas inteiras) do projeto que será cadastrado: ");
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

        } catch (IllegalStateException | PersistenceException e) {
            dDAO.rollback();
            pDAO.rollback();
            e.printStackTrace();
        } finally {
            dDAO.close();
            pDAO.close();
        }
    }

    public static void cadastrarFuncionarios() {
        numDepartamento = null;
        String nomeFuncionario = null;
        String endFuncionario = null;
        String sexoFuncionario = null;
        String dataNascFuncionario = null;
        Double salario = null;

        listarDepartamentos();

        try {
            dDAO.beginTransaction();

            System.out.println("Digite o número do departamento que o funcionário pertencerá: ");
            numDepartamento = Long.parseLong(scanner.nextLine());
            System.out.println("Digite o nome do funcionário que será cadastrado: ");
            nomeFuncionario = scanner.nextLine();
            System.out.println("Digite o endereço do funcionário que será cadastrado: ");
            endFuncionario = scanner.nextLine();
            System.out.println("Digite o sexo do funcionário que será cadastrado: ");
            sexoFuncionario = scanner.nextLine();
            System.out.println("Digite a data de nascimento do funcionário que será cadastrado: ");
            dataNascFuncionario = scanner.nextLine();
            System.out.println("Digite o salário do funcionário que será cadastrado: ");
            salario = Double.parseDouble(scanner.nextLine());

            List<Departamento> departamentos = dDAO.findAll();

            if (numDepartamento != null) {
                for (Departamento departamento : departamentos) {
                    if (departamento.getNumero().equals(numDepartamento)) {
                        Funcionario funcionario = new Funcionario(nomeFuncionario, endFuncionario, sexoFuncionario, dataNascFuncionario, salario, departamento);
                        fDAO.save(funcionario);
                        fDAO.commit();
                        departamento.getFuncionarios().add(funcionario);
                        dDAO.save(departamento);
                        dDAO.commit();
                        break;
                    }
                }
            }

        } catch (IllegalStateException | PersistenceException e) {
            dDAO.rollback();
            fDAO.rollback();
            e.printStackTrace();
        } finally {
            dDAO.close();
            fDAO.close();
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

            if (idFuncionario != null) {
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
    private static void deletarDepartamento() {
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