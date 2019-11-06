import daos.DepartamentoDAO;
import daos.jpa.DepartamentoJPADAO;
import menus.MenuDepartamentos;
import menus.MenuFuncionarios;
import menus.MenuProjetos;
import models.Departamento;

import javax.persistence.PersistenceException;
import java.util.Scanner;

public class Principal {

    private static Scanner scanner;

    public static void main(String[] args) {

        boolean run = true;
        scanner = new Scanner(System.in);

        while (run) {
            System.out.println("----------MENU----------");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Menu Departamentos");
            System.out.println("2 - Menu Projetos");
            System.out.println("3 - Menu Funcionários");
            System.out.println("0 - Sair");
            System.out.println("----------------------------");
            System.out.print(">>> ");

            int menu = -1;

            try {
                menu = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            switch (menu) {
                case 1:
                    new MenuDepartamentos().menuDepartamento();
                    break;

                case 2:
                    new MenuProjetos().menuProjetos();
                    System.out.println("Menu Projetos");
                    break;

                case 3:
                    new MenuFuncionarios().menuFuncionarios();
                    System.out.println("Menu Funcionários");
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
}