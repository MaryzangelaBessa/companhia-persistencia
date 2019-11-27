package daos.jpa;

import daos.PesquisadorDAO;
import models.Pesquisador;

public class PesquisadorRedisDAO extends GenericRedisDAO<Pesquisador> implements PesquisadorDAO {

    public PesquisadorRedisDAO() {
        super(Pesquisador.class);
    }
}
