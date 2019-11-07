package daos.jpa;

import daos.FuncionarioDAO;
import models.Funcionario;

public class FuncionarioJPADAO extends GenericJPADAO<Funcionario> implements FuncionarioDAO {

    public FuncionarioJPADAO() {
        super(Funcionario.class);
    }
}
