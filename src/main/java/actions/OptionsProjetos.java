package actions;

import actions.contracts.OptionsProjetosI;
import daos.DepartamentoDAO;
import daos.ProjetoDAO;
import daos.jpa.DepartamentoJPADAO;
import daos.jpa.ProjetoJPADAO;
import models.Departamento;
import models.Projeto;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Scanner;

public class OptionsProjetos implements OptionsProjetosI {

    private static Scanner scanner = new Scanner(System.in);

    private static DepartamentoDAO dDAO = new DepartamentoJPADAO();
    private static ProjetoDAO pDAO = new ProjetoJPADAO();

    @Override
    public void cadastrarProjeto() {
        Long numDepartamento = null;
        String nomeProjeto = null;
        int horasDuracao;

        try {
            dDAO.beginTransaction();

            System.out.println("Digite o número do departamento que o projeto pertencerá: ");
            numDepartamento = Long.parseLong(scanner.nextLine());
            System.out.println("Digite o nome do projeto que será cadastrado: ");
            nomeProjeto = scanner.nextLine();
            System.out.println("Digite a duração (em horas inteiras) do projeto que será cadastrado: ");
            horasDuracao = Integer.parseInt(scanner.nextLine());

            List<Departamento> departamentos = dDAO.findAll();

            for (Departamento departamento : departamentos) {
                if (departamento.getNumero().equals(numDepartamento)) {
                    Projeto projeto = new Projeto(nomeProjeto, horasDuracao, departamento);
                    pDAO.save(projeto);
                    pDAO.commit();
                    departamento.getProjetos().add(projeto);
                    dDAO.save(departamento);
                    dDAO.commit();
                    break;
                }
            }

        } catch (IllegalStateException | PersistenceException e) {
            dDAO.rollback();
            pDAO.rollback();
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

            dDAO.beginTransaction();

            System.out.println("Digite o número do projeto a ser excluído: ");
            numProjeto = Long.parseLong(scanner.nextLine());

            List<Departamento> departamentos = dDAO.findAll();
            List<Projeto> projetos = pDAO.findAll();

            for (Projeto projeto : projetos) {
                if(projeto.getNumero().equals(numProjeto)){
                    pDAO.delete(projeto);
                    pDAO.commit();
                    for (Departamento departamento : departamentos) {
                        if (projeto.getDepartamento().getNumero().equals(departamento.getNumero())){
                            departamento.getProjetos().remove(projeto);
                            dDAO.commit();
                            break;
                        }
                    }
                }
            }
        } catch (IllegalStateException | PersistenceException e) {
            pDAO.rollback();
            dDAO.rollback();
            e.printStackTrace();
        } finally {
            pDAO.close();
            dDAO.close();
        }
    }
}
