import actions.OptionsDepartamento;
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
    private static ProjetoDAO pDAO = new ProjetoJPADAO();
    private static FuncionarioDAO fDAO = new FuncionarioJPADAO();
    private static DependenteDAO depDAO = new DependenteJPADAO();
    private static PesquisadorDAO pesqDAO = new PesquisadorJPADAO();
    private static SecretarioDAO secDAO = new SecretarioJPADAO();
    private static AuxLimpezaDAO auxDAO = new AuxLimpezaJPADAO();

    private static OptionsDepartamento od = new OptionsDepartamento();
    private static OptionsProjetos op = new OptionsProjetos();

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
                    //of.cadastrarFuncionario();
                    break;
                case 8:
                    scanner.nextLine();
                    listarFuncionarios();
                    break;

                case 9:
                    scanner.nextLine();
                    //of.listarFuncionarios();
                    //of.deletarFuncionario();
                    break;

                case 10:
                    scanner.nextLine();
                    //of.listarFuncionarios();
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

    private static void listarFuncionarios() {
        List<Funcionario> funcionarios = fDAO.findAll();
        fDAO.close();
        System.out.println("\n\n" + "Lista de Funcionários");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
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

//    public static void cadastrarFuncionarios() {
//        numDepartamento = null;
//        int tipoFuncionario = 0;
//        String nomeFuncionario = null;
//        String endFuncionario = null;
//        String sexoFuncionario = null;
//        String dataNascFuncionario = null;
//        Double salario = null;
//        String areaAtuacao = null;
//        String grauEscoladidade = null;
//        String cargo = null;
//        int horasJornadaTrabalho = 0;
//
//        listarDepartamentos();
//
//        try {
//            dDAO.beginTransaction();
//
//            List<Departamento> departamentos = dDAO.findAll();
//
//            System.out.println("Digite o número do departamento que o funcionário pertencerá: ");
//            numDepartamento = Long.parseLong(scanner.nextLine());
//            System.out.println("Digite o nome do funcionário que será cadastrado: ");
//            nomeFuncionario = scanner.nextLine();
//            System.out.println("Digite o endereço do funcionário que será cadastrado: ");
//            endFuncionario = scanner.nextLine();
//            System.out.println("Digite o sexo do funcionário que será cadastrado: ");
//            sexoFuncionario = scanner.nextLine();
//            System.out.println("Digite a data de nascimento do funcionário que será cadastrado: ");
//            dataNascFuncionario = scanner.nextLine();
//            System.out.println("Digite o salário do funcionário que será cadastrado: ");
//            salario = Double.parseDouble(scanner.nextLine());
//
//            System.out.println("Escolha um tipo de funcionário:");
//            System.out.println("1 - Pesquisador");
//            System.out.println("2 - Secretário");
//            System.out.println("3 - Auxiliar de limpeza");
//            tipoFuncionario = Integer.parseInt(scanner.nextLine());
//
//            switch (tipoFuncionario){
//                case 1:
//                    System.out.println("Digite a área de atuação que o pesquisador trabalhará: ");
//                    areaAtuacao = scanner.nextLine();
//
//                    if (numDepartamento != null) {
//                        for (Departamento departamento : departamentos) {
//                            if (departamento.getNumero().equals(numDepartamento)) {
//                                Pesquisador pesquisador = new Pesquisador(nomeFuncionario, endFuncionario, sexoFuncionario, dataNascFuncionario, salario, departamento, areaAtuacao);
//                                fDAO.save(pesquisador);
//                                fDAO.commit();
//                                pesqDAO.save(pesquisador);
//                                pesqDAO.commit();
//                                departamento.getFuncionarios().add(pesquisador);
//                                dDAO.save(departamento);
//                                dDAO.commit();
//                                break;
//                            }
//                        }
//                    }
//
//                    break;
//                case 2:
//                    System.out.println("Digite o grau de escolaridade que o secretário tem: ");
//                    grauEscoladidade = scanner.nextLine();
//
//                    if (numDepartamento != null) {
//                        for (Departamento departamento : departamentos) {
//                            if (departamento.getNumero().equals(numDepartamento)) {
//                                Secretario secretario = new Secretario(nomeFuncionario, endFuncionario, sexoFuncionario, dataNascFuncionario, salario, departamento, grauEscoladidade);
//                                fDAO.save(secretario);
//                                fDAO.commit();
//                                secDAO.save(secretario);
//                                secDAO.commit();
//                                departamento.getFuncionarios().add(secretario);
//                                dDAO.save(departamento);
//                                dDAO.commit();
//                                break;
//                            }
//                        }
//                    }
//
//                    break;
//                case 3:
//                    System.out.println("Digite o cargo que o auxiliar de limpeza ocupará: ");
//                    cargo = scanner.nextLine();
//                    System.out.println("Digite as horasa de jornada de trabalho que o auxiliar de limpeza terá: ");
//                    horasJornadaTrabalho = Integer.parseInt(scanner.nextLine());
//
//                    if (numDepartamento != null) {
//                        for (Departamento departamento : departamentos) {
//                            if (departamento.getNumero().equals(numDepartamento)) {
//                                AuxLimpeza auxLimpeza = new AuxLimpeza(nomeFuncionario, endFuncionario, sexoFuncionario, dataNascFuncionario, salario, departamento, cargo, horasJornadaTrabalho);
//                                fDAO.save(auxLimpeza);
//                                fDAO.commit();
//                                auxDAO.save(auxLimpeza);
//                                auxDAO.commit();
//                                departamento.getFuncionarios().add(auxLimpeza);
//                                dDAO.save(departamento);
//                                dDAO.commit();
//                                break;
//                            }
//                        }
//                    }
//
//                    break;
//                default:
//                    System.out.println("Tipo Inválido!");
//                    break;
//            }
//
//        } catch (IllegalStateException | PersistenceException e) {
//            dDAO.rollback();
//            fDAO.rollback();
//            e.printStackTrace();
//        } finally {
//            dDAO.close();
//            fDAO.close();
//        }
//    }

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
}