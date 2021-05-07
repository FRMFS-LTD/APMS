

/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao;

import dao.interfaces.UserDaoInterface;
import model.utilisateur;
import org.hibernate.SQLQuery;

import javax.persistence.Id;
import javax.persistence.Query;
import javax.xml.transform.Result;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends MainDao implements UserDaoInterface<utilisateur, Id> {

    public UserDao() {

    }



    @Override
    public void persist(utilisateur entity) {
        getCurrentSession().save(entity);
    }
    // add new user entity to database

    public  utilisateur findById(int id){
        utilisateur user = (utilisateur) getCurrentSession().get(utilisateur.class, (Serializable) id);
        return user;
    }
    // return a user by id



    //update a user using object, the object will be modified using setters of the model
    public void update(utilisateur entity){
        getCurrentSession().update(entity);

    }

    // delete using object
    public void delete(utilisateur entity){
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    // get all the users  from a database and store them into list
    public List<utilisateur> findAll(){
        List<utilisateur> users_list = (List<utilisateur>) getCurrentSession().createQuery("from utilisateur").list();
        return users_list;
    }


    // delete all the user in case we need to
    public void deleteAll() {
        List<utilisateur> entityList = findAll();
        for (utilisateur entity : entityList) {
            delete(entity);
        }
    }


    // find the logged user
    public ArrayList<utilisateur> loggedUser(String username, String password){

        Query query = getCurrentSession().getNamedQuery("logUser");

        query.setParameter("username", username);
        query.setParameter("password", password);

        List Result = query.getResultList();
        ArrayList<utilisateur> results = (ArrayList<utilisateur>) Result;

        return results;
    }

    // get the user by email so we can use the password reset mechanism
    public utilisateur getUserByEmail(String mail,String Cin){

        Query query = getCurrentSession().getNamedQuery("FindUserToReset");
        query.setParameter("mail", mail);
        query.setParameter("cin", Cin);

        List Result = query.getResultList();
        utilisateur user = (utilisateur) Result.get(0);


        return user;
    }





}
