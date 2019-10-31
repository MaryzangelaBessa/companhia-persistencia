package daos.jpa;

import daos.SecretarioDAO;
import models.Secretario;

public class SecretarioJPADAO extends GenericJPADAO<Secretario> implements SecretarioDAO {

    public SecretarioJPADAO() {
        super(Secretario.class);
    }
}
