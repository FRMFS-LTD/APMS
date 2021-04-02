package Ddao;

import Ddao.Interfaces.AbonnementDaoInterface;
import Model.Abonnement;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class AbonnementDao extends MainDao implements AbonnementDaoInterface<Abonnement, Id> {

    public AbonnementDao() {

    }

    @Override
    public void add(Abonnement entite) {
        getCurrentSession().save(entite);
    }

    @Override
    public void modifier(Abonnement entite) {
        getCurrentSession().update(entite);
    }

    @Override
    public Abonnement findById(int id) {
        Abonnement abonn = (Abonnement) getCurrentSession().get(Abonnement.class, (Serializable) id);
        return abonn;
    }

    @Override
    public void remove(Abonnement entite) {
        getCurrentSession().delete(entite);
    }

    @Override
    public List<Abonnement> findAll() {
        List<Abonnement> listAbonnement = getCurrentSession().createQuery("from abonnement ").list() ;
        return listAbonnement;
    }

    @Override
    public void removeAll() {
        List<Abonnement> entiteList =findAll() ;
        for (Abonnement entite : entiteList){
            remove(entite);
        }
    }

}
