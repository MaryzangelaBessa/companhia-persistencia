package menus;

import java.util.Scanner;

public class MenuDependentes {
    private static Scanner scanner;

    public void menuDependentes(){
        scanner = new Scanner(System.in);

        while (true){
            System.out.println("\n---MENU DEPENDENTES----");
            System.out.println("1 - Cadastrar Dependente");
            System.out.println("2 - Listar Dependentes");
            System.out.println("3 - Deletar Dependente");
            System.out.println("0 - Voltar");
            System.out.println("\n-----------------------");

            int menu = scanner.nextInt();

            switch (menu){
                case 1:
                    scanner.nextLine();
                    System.out.println("Cadastrar Dependente");
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Listar Dependentes");
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Deletar Dependente");
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
