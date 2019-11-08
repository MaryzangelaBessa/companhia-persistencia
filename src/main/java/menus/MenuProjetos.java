package menus;

import actions.OptionsDepartamento;
import actions.OptionsFuncionarios;
import actions.OptionsProjetos;

import java.util.Scanner;

public class MenuProjetos {
    private static Scanner scanner;

    OptionsDepartamento od = new OptionsDepartamento();
    OptionsProjetos op = new OptionsProjetos();

    public void menuProjetos(){
        scanner = new Scanner(System.in);

        while (true){
            System.out.println("\n-----MENU PROJETOS-----");
            System.out.println("1 - Cadastrar Projeto");
            System.out.println("2 - Listar Projetos");
            System.out.println("3 - Deletar Projeto");
            System.out.println("0 - Voltar");
            System.out.println("-----------------------");

            int menu = scanner.nextInt();

            switch (menu){
                case 1:
                    scanner.nextLine();
                    od.listarDepartamentos();
                    op.cadastrarProjeto();
                    break;

                case 2:
                    scanner.nextLine();
                    op.listarProjetos();
                    break;

                case 3:
                    scanner.nextLine();
                    op.listarProjetos();
                    op.deletarProjeto();
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
