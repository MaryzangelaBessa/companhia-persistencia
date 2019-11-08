import actions.OptionsDepartamento;
import actions.OptionsDependentes;
import actions.OptionsFuncionarios;
import actions.OptionsProjetos;
import menus.MenuDependentes;
import menus.MenuFuncionários;

import java.util.Scanner;

public class Principal {

    private static Scanner scanner;


    private static OptionsDepartamento od = new OptionsDepartamento();
    private static OptionsProjetos op = new OptionsProjetos();

    private static MenuFuncionários mf = new MenuFuncionários();
    private static MenuDependentes mdep = new MenuDependentes();

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
            System.out.println("7 - Menu Funcionários");
            System.out.println("8 - Menu Dependentes");
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
                    mf.menuFuncionarios();
                    break;
                case 8:
                    scanner.nextLine();
                    mdep.menuDependentes();
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
}