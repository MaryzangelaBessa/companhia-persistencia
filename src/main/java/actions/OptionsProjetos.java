package actions;

import actions.contracts.OptionsProjetosI;
import daos.DepartamentoDAO;
import daos.ProjetoDAO;
import daos.redis.DepartamentoRedisDAO;
import daos.redis.ProjetoRedisDAO;
import models.Departamento;
import models.Projeto;
import java.util.List;
import java.util.Scanner;

public class OptionsProjetos implements OptionsProjetosI {

    private static Scanner scanner = new Scanner(System.in);

    private static DepartamentoDAO dDAO = new DepartamentoRedisDAO();
    private static ProjetoDAO pDAO = new ProjetoRedisDAO();

    @Override
    public void cadastrarProjeto() {
        Long numDepartamento = null;
        String nomeProjeto = null;
        int horasDuracao;

        try {

            System.out.println("Digite o número do departamento que o projeto pertencerá: ");
            numDepartamento = Long.parseLong(scanner.nextLine());
            System.out.println("Digite o nome do projeto que será cadastrado: ");
            nomeProjeto = scanner.nextLine();
            System.out.println("Digite a duração (em horas inteiras) do projeto que será cadastrado: ");
            horasDuracao = Integer.parseInt(scanner.nextLine());

            List<Departamento> departamentos = dDAO.findAll();

            for (Departamento departamento : departamentos) {
                if (departamento.getNumero().equals(numDepartamento)) {
                    Projeto projeto = new Projeto(nomeProjeto, horasDuracao, departamento.getNome());
                    pDAO.insert(projeto);
                    departamento.getProjetos().add(String.valueOf(projeto));
                    dDAO.insert(departamento);
                    break;
                }
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            dDAO.close();
            pDAO.close();
        }
    }

    @Override
    public void listarProjetos() {
        List<Projeto> projetos = pDAO.findAll();
        pDAO.close();
        System.out.println("\n\n" + "Lista de Projetos");
        for (Projeto projeto : projetos) {
            System.out.println(projeto);
        }
    }

    @Override
    public void deletarProjeto() {
        Long numProjeto;

        try {

            System.out.println("Digite o número do projeto a ser excluído: ");
            numProjeto = Long.parseLong(scanner.nextLine());

            List<Departamento> departamentos = dDAO.findAll();
            List<Projeto> projetos = pDAO.findAll();

            for (Projeto projeto : projetos) {
                if(projeto.getNumero().equals(numProjeto)){
                    pDAO.delete(projeto);
                    for (Departamento departamento : departamentos) {
                        if (projeto.getDepartamento().equals(departamento.getNome())){
                            departamento.getProjetos().remove(projeto);
                            break;
                        }
                    }
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            pDAO.close();
            dDAO.close();
        }
    }
}
