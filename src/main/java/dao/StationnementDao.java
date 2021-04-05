/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao;

import dao.interfaces.StationnementDaoInterface;
import model.Stationnement;


import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class StationnementDao extends MainDao implements StationnementDaoInterface<Stationnement, Id> {


    public StationnementDao() { }

    @Override
    public void persist(Stationnement entity){ getCurrentSession().save(entity);
    }

    @Override
    public Stationnement findById(int id) {
        Stationnement stationnement = (Stationnement) getCurrentSession().get(Stationnement.class, (Serializable) id);
        return stationnement;
    }

    @Override
    public void update(Stationnement entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void delete(Stationnement entity) {
        getCurrentSession().delete(entity);
    }
    /* "select * from tableName"*/
    @Override
    public List<Stationnement> findAll() {
        List<Stationnement> stationnement_list = (List<Stationnement>) getCurrentSession().createQuery("from Stationnement").list();
        return stationnement_list;
    }

    @Override
    public void deleteAll() {
        List<Stationnement> entityList = findAll();
        for (Stationnement entity : entityList) {
            delete(entity);
        }
    }

}
