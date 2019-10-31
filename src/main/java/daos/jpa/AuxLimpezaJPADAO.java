package daos.jpa;

import daos.AuxLimpezaDAO;
import models.AuxLimpeza;

public class AuxLimpezaJPADAO extends GenericJPADAO<AuxLimpeza> implements AuxLimpezaDAO {

    public AuxLimpezaJPADAO() {
        super(AuxLimpeza.class);
    }
}
