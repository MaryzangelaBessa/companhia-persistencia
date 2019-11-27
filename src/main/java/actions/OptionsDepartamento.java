package actions;

import actions.contracts.OptionsDepartamentoI;
import daos.DepartamentoDAO;
import daos.jpa.DepartamentoRedisDAO;
import models.Departamento;
import java.util.List;
import java.util.Scanner;

public class OptionsDepartamento implements OptionsDepartamentoI {

    private static Scanner scanner = new Scanner(System.in);
    private static DepartamentoDAO dDAO = new DepartamentoRedisDAO();

    @Override
    public void cadastrarDepartamento() {
        String nomeDepartamento = null;
        try {
            dDAO.beginTransaction();
            System.out.println("Digite o nome do departamento a ser cadastrado: ");
            nomeDepartamento = scanner.nextLine();
            dDAO.save(new Departamento(nomeDepartamento));
            dDAO.commit();
        } catch (IllegalStateException e) {
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
        Long numDepartamento = null;
        try {
            dDAO.beginTransaction();
            System.out.println("Digite o número do departamento a ser excluído: ");
            numDepartamento = Long.parseLong(scanner.nextLine());
            List<Departamento> departamentos = dDAO.findAll();
            for (Departamento departamento : departamentos) {
                if (departamento.getNumero().equals(numDepartamento)) {
                    dDAO.delete(departamento);
                    dDAO.commit();
                    break;
                }
            }
        } catch (IllegalStateException e) {
            dDAO.rollback();
            e.printStackTrace();
        } finally {
            dDAO.close();
        }
    }
}
