package daos.jpa;

import daos.DependenteDAO;
import models.Dependente;

public class DependenteJPADAO extends GenericJPADAO<Dependente> implements DependenteDAO {

    public DependenteJPADAO() {
        super(Dependente.class);
    }
}
