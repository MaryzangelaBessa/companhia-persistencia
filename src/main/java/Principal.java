import daos.DepartamentoDAO;
import daos.jpa.DepartamentoJPADAO;
import models.Departamento;

import javax.persistence.PersistenceException;

public class Principal {
    public static void main(String[] args) {
        inserirDepartamento();
    }

    public static void inserirDepartamento(){
        DepartamentoDAO dDAO = new DepartamentoJPADAO();

        try {
            dDAO.beginTransaction();
            dDAO.save(new Departamento("Computação"));
            dDAO.commit();
        }catch (IllegalStateException | PersistenceException e){
            dDAO.rollback();
            e.printStackTrace();
        }finally {
            dDAO.close();
        }
    }
}