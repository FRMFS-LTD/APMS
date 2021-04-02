package dao;

import dao.interfaces.AbonnementDaoInterface;
import model.abonnement;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class AbonnementDao extends MainDao implements AbonnementDaoInterface<abonnement , Id> {

    public AbonnementDao() {

    }

    @Override
    public void persist(abonnement entite) {
        getCurrentSession().save(entite);
    }

    @Override
    public void update(abonnement entite) {
        getCurrentSession().update(entite);
    }

    @Override
    public abonnement findById(int id) {
        abonnement abonn = (abonnement) getCurrentSession().get(abonnement.class, (Serializable) id);
        return abonn;
    }

    @Override
    public void delete(abonnement entite) {
        getCurrentSession().delete(entite);
    }

    @Override
    public List<abonnement> findAll() {
        List<abonnement> ListAbonnement = getCurrentSession().createQuery("from abonnement ").list() ;
        return ListAbonnement;
    }

    @Override
    public void deleteAll() {
        List<abonnement> entiteList =findAll() ;
        for (abonnement entite : entiteList){
            delete(entite);
        }
    }

}
