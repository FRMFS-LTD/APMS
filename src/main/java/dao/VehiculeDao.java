package dao;

import dao.interfaces.VehiculeDaoInterface;
import model.Vehicule;
import model.utilisateur;
import org.jboss.jandex.Main;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;


public class VehiculeDao extends MainDao implements VehiculeDaoInterface<Vehicule, Id> {

    public VehiculeDao() {
    }


    @Override
    public void persist(Vehicule entity){
        getCurrentSession().save(entity);
    }

    // supply id to get Vehicule Object
    public Vehicule findById(int id){
        Vehicule vehicule = (Vehicule) getCurrentSession().get(Vehicule.class, (Serializable) id);
        return vehicule;
    }



    public void update(Vehicule entity){
        getCurrentSession().update(entity);
    }

    public void delete(Vehicule entity){
        getCurrentSession().delete(entity);
    }

    /* "select * from tableName"*/

    public List<Vehicule> findAll(){
        List<Vehicule> vehicule_list = (List<Vehicule>) getCurrentSession().createQuery("from Vehicule").list();
        return vehicule_list;
    }


    public void deleteAll() {
        List<Vehicule> entityList = findAll();

        for (Vehicule entity : entityList) {
            delete(entity);
        }
    }











}
