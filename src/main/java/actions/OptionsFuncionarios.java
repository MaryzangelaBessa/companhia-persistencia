package actions;

import actions.contracts.OptionsFuncionariosI;
import daos.*;
import daos.redis.*;
import models.*;
import java.util.List;
import java.util.Scanner;

public class OptionsFuncionarios implements OptionsFuncionariosI {
    private static Scanner scanner = new Scanner(System.in);

    private static DepartamentoDAO dDAO = new DepartamentoRedisDAO();
    private static FuncionarioDAO fDAO = new FuncionarioRedisDAO();
    private static PesquisadorDAO pesqDAO = new PesquisadorRedisDAO();
    private static SecretarioDAO secDAO = new SecretarioRedisDAO();
    private static AuxLimpezaDAO auxDAO = new AuxLimpezaRedisDAO();


    @Override
    public void cadastrarFuncionario() {
        Long numDepartamento = null;
        int tipoFuncionario = 0;
        String nomeFuncionario = null;
        String endFuncionario = null;
        String sexoFuncionario = null;
        String dataNascFuncionario = null;
        String salario = null;
        String areaAtuacao = null;
        String grauEscoladidade = null;
        String cargo = null;
        int horasJornadaTrabalho = 0;

        try {

            List<Departamento> departamentos = dDAO.findAll();

            System.out.println("Digite o número do departamento que o funcionário pertencerá: ");
            numDepartamento = Long.parseLong(scanner.nextLine());
            String nDep = String.valueOf(numDepartamento);
            System.out.println("Digite o nome do funcionário que será cadastrado: ");
            nomeFuncionario = scanner.nextLine();
            System.out.println("Digite o endereço do funcionário que será cadastrado: ");
            endFuncionario = scanner.nextLine();
            System.out.println("Digite o sexo do funcionário que será cadastrado: ");
            sexoFuncionario = scanner.nextLine();
            System.out.println("Digite a data de nascimento do funcionário que será cadastrado: ");
            dataNascFuncionario = scanner.nextLine();
            System.out.println("Digite o salário do funcionário que será cadastrado: ");
            salario = scanner.nextLine();

            System.out.println("Escolha um tipo de funcionário:");
            System.out.println("1 - Pesquisador");
            System.out.println("2 - Secretário");
            System.out.println("3 - Auxiliar de limpeza");
            tipoFuncionario = Integer.parseInt(scanner.nextLine());

            switch (tipoFuncionario){
                case 1:
                    System.out.println("Digite a área de atuação que o pesquisador trabalhará: ");
                    areaAtuacao = scanner.nextLine();

                    for (Departamento departamento : departamentos) {
                        if (departamento.getNumero().equals(numDepartamento)) {

                            Pesquisador pesquisador = new Pesquisador(nomeFuncionario, endFuncionario, sexoFuncionario, dataNascFuncionario, salario, nDep, areaAtuacao);
                            fDAO.insert(pesquisador);
                            pesqDAO.insert(pesquisador);
                            departamento.getFuncionarios().add(String.valueOf(pesquisador));
                            dDAO.insert(departamento);
                            break;
                        }
                    }

                    break;
                case 2:
                    System.out.println("Digite o grau de escolaridade que o secretário tem: ");
                    grauEscoladidade = scanner.nextLine();

                    for (Departamento departamento : departamentos) {
                        if (departamento.getNumero().equals(numDepartamento)) {
                            Secretario secretario = new Secretario(nomeFuncionario, endFuncionario, sexoFuncionario, dataNascFuncionario, salario, nDep, grauEscoladidade);
                            fDAO.insert(secretario);
                            secDAO.insert(secretario);
                            departamento.getFuncionarios().add(String.valueOf(secretario));
                            dDAO.insert(departamento);
                            break;
                        }
                    }

                    break;
                case 3:
                    System.out.println("Digite o cargo que o auxiliar de limpeza ocupará: ");
                    cargo = scanner.nextLine();
                    System.out.println("Digite as horas de jornada de trabalho que o auxiliar de limpeza terá: ");
                    horasJornadaTrabalho = Integer.parseInt(scanner.nextLine());

                    for (Departamento departamento : departamentos) {
                        if (departamento.getNumero().equals(numDepartamento)) {
                            AuxLimpeza auxLimpeza = new AuxLimpeza(nomeFuncionario, endFuncionario, sexoFuncionario, dataNascFuncionario, salario, nDep, cargo, horasJornadaTrabalho);
                            fDAO.insert(auxLimpeza);
                            auxDAO.insert(auxLimpeza);
                            departamento.getFuncionarios().add(String.valueOf(auxLimpeza));
                            dDAO.insert(departamento);
                            break;
                        }
                    }

                    break;
                default:
                    System.out.println("Tipo Inválido!");
                    break;
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            dDAO.close();
            fDAO.close();
        }
    }

    @Override
    public void listarFuncionarios() {
        List<Funcionario> funcionarios = fDAO.findAll();
        fDAO.close();
        System.out.println("\n\n" + "Lista de Funcionários");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    @Override
    public void listarPesquisadores() {
        List<Pesquisador> pesquisadores = pesqDAO.findAll();
        pesqDAO.close();
        System.out.println("\n\n" + "Lista de Pesquisadores");
        for (Pesquisador pesquisador : pesquisadores) {
            System.out.println(pesquisador);
        }
    }

    @Override
    public void listarSecretarios() {
        List<Secretario> secretarios = secDAO.findAll();
        secDAO.close();
        System.out.println("\n\n" + "Lista de Secretários");
        for (Secretario secretario : secretarios) {
            System.out.println(secretario);
        }
    }

    @Override
    public void listarAuxiliaresLimpeza() {
        List<AuxLimpeza> auxiliaresLimpeza = auxDAO.findAll();
        auxDAO.close();
        System.out.println("\n\n" + "Lista de Auxiliares de Limpeza");
        for (AuxLimpeza auxLimpeza : auxiliaresLimpeza) {
            System.out.println(auxLimpeza);
        }
    }

    @Override
    public void deletarFuncionario() {
        Long idFuncionario;

        try {

            System.out.println("Digite o id do funcionário a ser excluído: ");
            idFuncionario = Long.parseLong(scanner.nextLine());

            List<Departamento> departamentos = dDAO.findAll();
            List<Funcionario> funcionarios = fDAO.findAll();

            for (Funcionario funcionario : funcionarios) {
                if(funcionario.getId().equals(idFuncionario)){
                    if (funcionario instanceof Pesquisador){
                        pesqDAO.delete((Pesquisador) funcionario);
                    } else if (funcionario instanceof Secretario) {
                        secDAO.delete((Secretario) funcionario);
                    } else if (funcionario instanceof AuxLimpeza){
                        auxDAO.delete((AuxLimpeza) funcionario);
                    }
                    fDAO.delete(funcionario);
                    for (Departamento departamento : departamentos) {
                        if (funcionario.getDepartamento().equals(departamento.getNome())){
                            departamento.getFuncionarios().remove(funcionario);
                            break;
                        }
                    }
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            pesqDAO.close();
            secDAO.close();
            auxDAO.close();
            fDAO.close();
            dDAO.close();
        }
    }
}
