/*
 * Copyright (c) 2021 // programmed by Rachid Boufous
 */

package dao.Services;

import dao.VehiculeDao;
import model.Vehicule;

import java.io.Serializable;
import java.util.List;


public class VehiculeService {

    private static VehiculeDao vehiculedao;

    public VehiculeService() {
        vehiculedao = new VehiculeDao();
    }


    public void persist(Vehicule entity){
        vehiculedao.openCurrentSessionWithTransaction();
        vehiculedao.persist(entity);
        vehiculedao.closeCurrentSessionWithTransaction();
    }

    public Vehicule findById(int id){
        vehiculedao.openCurrentSession();
        Vehicule vehicule = vehiculedao.findById(id);
        vehiculedao.closeCurrentSession();

        return vehicule;
    }

    public void update(Vehicule entity){
        vehiculedao.openCurrentSessionWithTransaction();
        vehiculedao.update(entity);
        vehiculedao.openCurrentSessionWithTransaction();
    }

    public void delete(int id){
        vehiculedao.openCurrentSessionWithTransaction();
        Vehicule vehicule =  vehiculedao.findById(id);
        vehiculedao.delete(vehicule);
        vehiculedao.closeCurrentSessionWithTransaction();
    }

    public List<Vehicule> findAll(){
        vehiculedao.openCurrentSession();
        List<Vehicule> list_vehicules = vehiculedao.findAll();
        vehiculedao.openCurrentSession();
        return list_vehicules;
    }


    public void deleteAll(){
        vehiculedao.openCurrentSession();
        vehiculedao.deleteAll();
        vehiculedao.closeCurrentSession();
    }




}
