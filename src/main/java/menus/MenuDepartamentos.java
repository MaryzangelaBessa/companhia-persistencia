package menus;

import actions.OptionsDepartamento;

import java.util.Scanner;

public class MenuDepartamentos {

    private static Scanner scanner;

    OptionsDepartamento od = new OptionsDepartamento();

    public void menuDepartamentos(){
        scanner = new Scanner(System.in);

        while (true){
            System.out.println("\n-----MENU DEPARTAMENTOS-----");
            System.out.println("1 - Cadastrar Departamento");
            System.out.println("2 - Listar Departamentos");
            System.out.println("3 - Deletar Departamento");
            System.out.println("0 - Voltar");
            System.out.println("----------------------------");
            System.out.println(">>> ");

            int menu = scanner.nextInt();

            switch (menu){
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

                case 0:
                    return;

                default:
                    System.out.println("Escolha Invalida!");
                    break;
            }
        }
    }
}
