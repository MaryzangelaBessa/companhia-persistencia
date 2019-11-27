package daos.jpa;

import daos.DepartamentoDAO;
import models.Departamento;

public class DepartamentoRedisDAO extends GenericRedisDAO<Departamento> implements DepartamentoDAO {

    public DepartamentoRedisDAO() { super(Departamento.class); }
}
