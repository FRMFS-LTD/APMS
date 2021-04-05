/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.interfaces;

import model.Stationnement;
import model.Vehicule;

import java.util.List;

public interface StationnementDaoInterface<T,T1> {
    public void persist(T entity);

    public Stationnement findById(int id);

    public void update(T entity);

    public void delete(T entity);

    public List<Stationnement> findAll();

    public void deleteAll();
}
