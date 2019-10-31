package daos.jpa;

import daos.ProjetoDAO;
import models.Projeto;

public class ProjetoJPADAO extends GenericJPADAO<Projeto> implements ProjetoDAO {

    public ProjetoJPADAO() {
        super(Projeto.class);
    }
}
