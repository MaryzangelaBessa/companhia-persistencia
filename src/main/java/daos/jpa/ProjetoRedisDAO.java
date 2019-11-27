package daos.jpa;

import daos.ProjetoDAO;
import models.Projeto;

public class ProjetoRedisDAO extends GenericRedisDAO<Projeto> implements ProjetoDAO {

    public ProjetoRedisDAO() {
        super(Projeto.class);
    }
}
