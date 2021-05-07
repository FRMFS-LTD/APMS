

/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.interfaces;

import model.utilisateur;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface UserDaoInterface<T, T1> {
    // this inerface is represent an abstraction class for
    // all the functions and models related(user, userDAO)
    
    public void persist(T entity);

    public void update(T entity);

    public T findById(int id);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();

    public ArrayList<utilisateur> loggedUser(String userName, String password);

    public utilisateur getUserByEmail(String mail,String Cin);



}
