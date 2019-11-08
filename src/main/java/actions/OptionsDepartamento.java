package actions;

import daos.DepartamentoDAO;
import daos.jpa.DepartamentoJPADAO;
import models.Departamento;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Scanner;

public class OptionsDepartamento implements actions.contracts.OptionsDepartamento {

    private static Scanner scanner = new Scanner(System.in);
    private static DepartamentoDAO dDAO = new DepartamentoJPADAO();
    private static Long numDepartamento = null;

    @Override
    public void inserirDepartamento() {
        String nomeDepartamento = null;
        try {
            dDAO.beginTransaction();
            System.out.println("Digite o nome do departamento a ser cadastrado: ");
            nomeDepartamento = scanner.nextLine();
            dDAO.save(new Departamento(nomeDepartamento));
            dDAO.commit();
        } catch (IllegalStateException | PersistenceException e) {
            dDAO.rollback();
            e.printStackTrace();
        } finally {
            dDAO.close();
        }
    }

    @Override
    public void listarDepartamentos() {
        List<Departamento> departamentos = dDAO.findAll();
        dDAO.close();
        System.out.println("\n\n" + "Lista de Departamentos");
        for (Departamento departamento : departamentos) {
            System.out.println(departamento);
        }
    }

    @Override
    public void deletarDepartamento() {

    }
}
