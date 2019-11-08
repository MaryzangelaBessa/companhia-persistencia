package menus;

import actions.OptionsDepartamento;
import actions.OptionsFuncionarios;

import java.util.Scanner;

public class MenuFuncionarios {
    private static Scanner scanner;

    OptionsDepartamento od = new OptionsDepartamento();
    OptionsFuncionarios of = new OptionsFuncionarios();

    public void menuFuncionarios(){
        scanner = new Scanner(System.in);

        while (true){
            System.out.println("\n---MENU FUNCIONÁRIOS---");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("3 - Listar Pesquisadores");
            System.out.println("4 - Listar Secretários");
            System.out.println("5 - Listar Auxiliares de Limpeza");
            System.out.println("6 - Deletar Funcionário");
            System.out.println("7 - Ver Projetos de um Pesquisador");
            System.out.println("8 - Ver Dependentes de um Funcionário");
            System.out.println("0 - Voltar");
            System.out.println("-----------------------");

            int menu = scanner.nextInt();

            switch (menu){
                case 1:
                    scanner.nextLine();
                    od.listarDepartamentos();
                    of.cadastrarFuncionario();
                    break;
                case 2:
                    scanner.nextLine();
                    of.listarFuncionarios();
                    break;
                case 3:
                    scanner.nextLine();
                    of.listarPesquisadores();
                    break;
                case 4:
                    scanner.nextLine();
                    of.listarSecretarios();
                    break;
                case 5:
                    scanner.nextLine();
                    of.listarAuxiliaresLimpeza();
                    break;
                case 6:
                    scanner.nextLine();
                    of.listarFuncionarios();
                    of.deletarFuncionario();
                    break;
                case 7:
                    scanner.nextLine();
                    System.out.println("Ver Projetos de um Pesquisador");
                    break;
                case 8:
                    scanner.nextLine();
                    System.out.println("Ver Dependentes de um Funcionário");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Escolha Invalida!");
                    break;
            }
        }

    }
}
