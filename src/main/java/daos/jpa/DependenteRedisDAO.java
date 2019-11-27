package daos.jpa;

import daos.DependenteDAO;
import models.Dependente;

public class DependenteRedisDAO extends GenericRedisDAO<Dependente> implements DependenteDAO {

    public DependenteRedisDAO() {
        super(Dependente.class);
    }
}
