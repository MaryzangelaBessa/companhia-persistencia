import menus.MenuDepartamentos;
import menus.MenuDependentes;
import menus.MenuFuncionarios;
import menus.MenuProjetos;

import java.util.Scanner;

public class Principal {

    private static Scanner scanner;

    private static MenuDepartamentos md = new MenuDepartamentos();
    private static MenuProjetos mp = new MenuProjetos();
    private static MenuFuncionarios mf = new MenuFuncionarios();
    private static MenuDependentes mdep = new MenuDependentes();

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n----------MENU----------");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Menu Departamentos");
            System.out.println("2 - Menu Projetos");
            System.out.println("3 - Menu Funcionários");
            System.out.println("4 - Menu Dependentes");
            System.out.println("0 - Sair");
            System.out.println("---------------------------");
            System.out.println(">>> ");

            int id = -1;
            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    scanner.nextLine();
                    md.menuDepartamentos();
                    break;

                case 2:
                    scanner.nextLine();
                    mp.menuProjetos();
                    break;

                case 3:
                    scanner.nextLine();
                    mf.menuFuncionarios();
                    break;
                case 4:
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