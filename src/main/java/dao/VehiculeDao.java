

/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao;

import dao.interfaces.VehiculeDaoInterface;
import model.Vehicule;
import model.utilisateur;
import org.hibernate.query.Query;
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

    public int AvVehiculeNumber(){
        Query query = getCurrentSession().createQuery("SELECT count(*) FROM Vehicule");

        int Result = Integer.parseInt(query.getSingleResult().toString());
        return Result;
    }

    public int SubVehiculeNumber(){
        Query query = getCurrentSession().createSQLQuery("SELECT count(*) FROM vehicule where abonnement_id is not null");

        int Result = Integer.parseInt(query.getSingleResult().toString());
        return Result;
    }

    public int UnSubVehiculeNumber(){
        Query query = getCurrentSession().createSQLQuery("SELECT count(*) FROM vehicule where abonnement_id is null");

        int Result = Integer.parseInt(query.getSingleResult().toString());
        return Result;
    }











}
