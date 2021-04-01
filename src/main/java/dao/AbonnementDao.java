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
    public void add(abonnement entite) {
        getCurrentSession().save(entite);
    }

    @Override
    public void modifier(abonnement entite) {
        getCurrentSession().update(entite);
    }

    @Override
    public abonnement findById(int id) {
        abonnement abonn = (abonnement) getCurrentSession().get(abonnement.class, (Serializable) id);
        return abonn;
    }

    @Override
    public void remove(abonnement entite) {
        getCurrentSession().delete(entite);
    }

    @Override
    public List<abonnement> findAll() {
        List<abonnement> ListAbonnement = getCurrentSession().createQuery("from abonnement ").list() ;
        return ListAbonnement;
    }

    @Override
    public void removeAll() {
        List<abonnement> entiteList =findAll() ;
        for (abonnement entite : entiteList){
            remove(entite);
        }
    }

}
