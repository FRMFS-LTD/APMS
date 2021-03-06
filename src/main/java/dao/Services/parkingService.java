package dao.Services;
/*
 * Copyright (c) 2021.
 * programmed by Fadoua Abdoulmoulah.
 * for FRMFS-ltd organisation
 *
 */

import dao.parkingDao;
import model.parking;

import java.util.List;

public class parkingService {
    private static parkingDao parkingDao;

    public parkingService() {
        parkingDao = new parkingDao();
    }

    public void persist(parking Pg){
        parkingDao.openCurrentSessionWithTransaction();
        parkingDao.persist(Pg);
        parkingDao.closeCurrentSessionWithTransaction();
    }

    public void update(parking Pg){
        parkingDao.openCurrentSessionWithTransaction();
        parkingDao.update(Pg);
        parkingDao.closeCurrentSessionWithTransaction();
    }

    public parking findById(int id){
        parkingDao.openCurrentSession();
        parking parking = parkingDao.findById(id);
        parkingDao.closeCurrentSession();
        return parking;
    }

    public void delete(int id){
        parkingDao.openCurrentSessionWithTransaction();
        parking parking = parkingDao.findById(id);
        parkingDao.delete(parking);
        parkingDao.closeCurrentSessionWithTransaction();
    }

    public List<parking> findAll(){
        parkingDao.openCurrentSession();
        List<parking> tests = parkingDao.findAll();
        parkingDao.closeCurrentSession();
        return tests;
    }

    public void deleteAll(){
        parkingDao.openCurrentSession();
        parkingDao.deleteAll();
        parkingDao.closeCurrentSession();
    }
    public void deleteAllWithTransaction(){
        parkingDao.openCurrentSessionWithTransaction();
        parkingDao.deleteAll();
        parkingDao.closeCurrentSessionWithTransaction();

    }


    public int findParkingCount(){
        parkingDao.openCurrentSession();
        int count = parkingDao.findParkingCount();
        parkingDao.closeCurrentSession();
        return count;
    }

    public int AvPlaceNumber(){
        parkingDao.openCurrentSessionWithTransaction();
        int count = parkingDao.AvPlaceNumber();
        parkingDao.closeCurrentSessionWithTransaction();
        return count;
    }
    public parkingDao parkingDao(){
        return parkingDao;
    }
}
