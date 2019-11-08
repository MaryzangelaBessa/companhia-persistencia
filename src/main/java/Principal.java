import actions.OptionsDepartamento;
import actions.OptionsProjetos;
import menus.MenuDependentes;
import menus.MenuFuncionarios;
import menus.MenuProjetos;

import java.util.Scanner;

public class Principal {

    private static Scanner scanner;


    private static OptionsDepartamento od = new OptionsDepartamento();

    private static MenuFuncionarios mf = new MenuFuncionarios();
    private static MenuDependentes mdep = new MenuDependentes();
    private static MenuProjetos mp = new MenuProjetos();

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n----------MENU----------");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Departamento");
            System.out.println("2 - Listar Departamentos");
            System.out.println("3 - Deletar Departamento");
            System.out.println("4 - Menu Projetos");
            System.out.println("5 - Menu Funcionários");
            System.out.println("6 - Menu Dependentes");
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
                    mp.menuProjetos();
                    break;

                case 5:
                    scanner.nextLine();
                    mf.menuFuncionarios();
                    break;
                case 6:
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