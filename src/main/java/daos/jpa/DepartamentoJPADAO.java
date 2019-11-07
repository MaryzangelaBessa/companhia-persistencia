package daos.jpa;

import daos.DepartamentoDAO;
import models.Departamento;

public class DepartamentoJPADAO extends GenericJPADAO<Departamento> implements DepartamentoDAO {

    public DepartamentoJPADAO() { super(Departamento.class); }
}
