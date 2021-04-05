/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;

import dao.StationnementDao;
import model.Stationnement;
import model.Vehicule;

import java.util.List;

public class StationnementService {

    private static StationnementDao stationnementDao;

    public StationnementService() {
        stationnementDao = new StationnementDao();
    }

    public void persist(Stationnement entity){
        stationnementDao.openCurrentSessionWithTransaction();
        stationnementDao.persist(entity);
        stationnementDao.closeCurrentSessionWithTransaction();
    }

    public Stationnement findById(int id){
        stationnementDao.openCurrentSession();
        Stationnement stationnement = stationnementDao.findById(id);
        stationnementDao.closeCurrentSession();

        return stationnement;
    }
    public void update(Stationnement entity){
        stationnementDao.openCurrentSessionWithTransaction();
        stationnementDao.update(entity);
        stationnementDao.openCurrentSessionWithTransaction();
    }

    public void delete(int id){
        stationnementDao.openCurrentSessionWithTransaction();
        Stationnement stationnement =  stationnementDao.findById(id);
        stationnementDao.delete(stationnement);
        stationnementDao.closeCurrentSessionWithTransaction();
    }

    public List<Stationnement> findAll(){
        stationnementDao.openCurrentSession();
        List<Stationnement> list_stationnement = stationnementDao.findAll();
        stationnementDao.openCurrentSession();
        return list_stationnement;
    }


    public void deleteAll(){
        stationnementDao.openCurrentSession();
        stationnementDao.deleteAll();
        stationnementDao.closeCurrentSession();
    }
}
