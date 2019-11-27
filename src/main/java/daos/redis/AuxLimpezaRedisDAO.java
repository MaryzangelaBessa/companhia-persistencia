package daos.redis;

import daos.AuxLimpezaDAO;
import models.AuxLimpeza;

public class AuxLimpezaRedisDAO extends GenericRedisDAO<AuxLimpeza> implements AuxLimpezaDAO {

    public AuxLimpezaRedisDAO() {
        super(AuxLimpeza.class);
    }
}
