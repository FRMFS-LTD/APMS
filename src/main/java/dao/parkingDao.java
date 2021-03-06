package dao;

/*
 * Copyright (c) 2021.
 * programmed by Fadoua Abdoulmoulah.
 * for FRMFS-ltd organisation
 *
 */
import dao.interfaces.parkingDaoInterface;
import model.parking;
import model.utilisateur;
import org.hibernate.query.Query;

import javax.persistence.Id;

import java.io.Serializable;
import java.util.List;

public class parkingDao extends MainDao implements parkingDaoInterface<parking, Id>{
     public parkingDao(){

     }
     public void persist (parking park){
         getCurrentSession().save(park);
     }
     public parking findById(int id){
         parking park = (parking)getCurrentSession().get(parking.class, (Serializable) id);
         return park;

     }
     public void update(parking park){
         getCurrentSession().update(park);

     }
     public void delete(parking park){
         getCurrentSession().delete(park);
     }
     public List<parking> findAll(){
         List<parking> parkList = (List<parking>) getCurrentSession().createQuery("from parking").list();
         return parkList;
     }
     public void deleteAll(){
         List<parking> parkList = findAll();
         for (parking park : parkList){
             delete(park);
         }
     }

     public int findParkingCount(){
         return findAll().size();
     }

     public int AvPlaceNumber(){
         Query query = getCurrentSession().createQuery("SELECT sum(nbplace) FROM parking");

         int Result = Integer.parseInt(query.getSingleResult().toString());
         return Result;
     }



}
