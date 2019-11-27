package daos.jpa;

import daos.SecretarioDAO;
import models.Secretario;

public class SecretarioRedisDAO extends GenericRedisDAO<Secretario> implements SecretarioDAO {

    public SecretarioRedisDAO() {
        super(Secretario.class);
    }
}
