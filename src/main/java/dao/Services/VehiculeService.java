

/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;

import dao.VehiculeDao;
import model.Vehicule;

import java.io.Serializable;
import java.sql.ResultSet;
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
        vehiculedao.closeCurrentSessionWithTransaction();
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
        vehiculedao.closeCurrentSession();
        return list_vehicules;
    }


    public void deleteAll(){
        vehiculedao.openCurrentSession();
        vehiculedao.deleteAll();
        vehiculedao.closeCurrentSession();
    }

    public int AvVehiculeNumber(){
        vehiculedao.openCurrentSession();
        int count = vehiculedao.AvVehiculeNumber();
        vehiculedao.closeCurrentSession();
        return count;
    }

    public int SubVehiculeNumber() {
        vehiculedao.openCurrentSession();
        int count = vehiculedao.SubVehiculeNumber();
        vehiculedao.closeCurrentSession();
        return count;
    }

    public int UnSubVehiculeNumber(){
        vehiculedao.openCurrentSession();
        int count = vehiculedao.UnSubVehiculeNumber();
        vehiculedao.closeCurrentSession();
        return count;
    }



}
