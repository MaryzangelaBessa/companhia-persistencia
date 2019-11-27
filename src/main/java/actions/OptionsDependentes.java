package actions;

import actions.contracts.OptionsDependentesI;
import daos.DependenteDAO;
import daos.FuncionarioDAO;
import daos.jpa.DependenteRedisDAO;
import daos.jpa.FuncionarioRedisDAO;
import models.Dependente;
import models.Funcionario;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Scanner;

public class OptionsDependentes implements OptionsDependentesI {

    private static Scanner scanner = new Scanner(System.in);

    private static FuncionarioDAO fDAO = new FuncionarioRedisDAO();
    private static DependenteDAO depDAO = new DependenteRedisDAO();


    @Override
    public void cadastrarDependente() {
        Long idFuncionario = null;
        String nomeDependente = null;
        String sexoDependente = null;
        String dataNascDependente = null;

        List<Funcionario> funcionarios = fDAO.findAll();

        try {
            fDAO.beginTransaction();

            System.out.println("Digite o id do funcionário que o dependente pertencerá: ");
            idFuncionario = Long.parseLong(scanner.nextLine());
            System.out.println("Digite o nome do dependente que será cadastrado: ");
            nomeDependente = scanner.nextLine();
            System.out.println("Digite o sexo do dependente que será cadastrado: ");
            sexoDependente = scanner.nextLine();
            System.out.println("Digite a data de nascimento do dependente que será cadastrado: ");
            dataNascDependente = scanner.nextLine();

            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getId().equals(idFuncionario)) {
                    Dependente dependente = new Dependente(nomeDependente, sexoDependente, dataNascDependente, funcionario);
                    depDAO.save(dependente);
                    depDAO.commit();
                    funcionario.getDependentes().add(dependente);
                    fDAO.save(funcionario);
                    fDAO.commit();
                    break;
                }
            }

        } catch (IllegalStateException | PersistenceException e) {
            depDAO.rollback();
            fDAO.rollback();
            e.printStackTrace();
        } finally {
            depDAO.close();
            fDAO.close();
        }
    }

    @Override
    public void listarDependentes() {
        List<Dependente> dependentes = depDAO.findAll();
        depDAO.close();
        System.out.println("\n\n" + "Lista de Dependentes");
        for (Dependente dependente : dependentes) {
            System.out.println(dependente);
        }
    }

    @Override
    public void deletarDependente() {
        Long idDependente;

        try {

            fDAO.beginTransaction();

            System.out.println("Digite o id do dependente a ser excluído: ");
            idDependente = Long.parseLong(scanner.nextLine());

            List<Funcionario> funcionarios = fDAO.findAll();
            List<Dependente> dependentes = depDAO.findAll();

            for (Dependente dependente : dependentes) {
                if(dependente.getId().equals(idDependente)){
                    depDAO.delete(dependente);
                    depDAO.commit();
                    for (Funcionario funcionario : funcionarios) {
                        if (dependente.getFuncionario().getId().equals(funcionario.getId())){
                            funcionario.getDependentes().remove(dependente);
                            fDAO.commit();
                            break;
                        }
                    }
                }
            }
        } catch (IllegalStateException | PersistenceException e) {
            depDAO.rollback();
            fDAO.rollback();
            e.printStackTrace();
        } finally {
            depDAO.close();
            fDAO.close();
        }
    }
}
