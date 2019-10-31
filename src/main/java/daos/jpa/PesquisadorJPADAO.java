package daos.jpa;

import daos.PesquisadorDAO;
import models.Pesquisador;

public class PesquisadorJPADAO extends GenericJPADAO<Pesquisador> implements PesquisadorDAO {

    public PesquisadorJPADAO() {
        super(Pesquisador.class);
    }
}
