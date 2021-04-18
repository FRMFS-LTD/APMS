

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


    public  utilisateur findById(int id){
        utilisateur user = (utilisateur) getCurrentSession().get(utilisateur.class, (Serializable) id);
        return user;
    }



    public void update(utilisateur entity){
        getCurrentSession().update(entity);

    }

    public void delete(utilisateur entity){
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<utilisateur> findAll(){
        List<utilisateur> users_list = (List<utilisateur>) getCurrentSession().createQuery("from utilisateur").list();
        return users_list;
    }



    public void deleteAll() {
        List<utilisateur> entityList = findAll();
        for (utilisateur entity : entityList) {
            delete(entity);
        }
    }

    public ArrayList<utilisateur> loggedUser(String username, String password){

        Query query = getCurrentSession().getNamedQuery("logUser");

        query.setParameter("username", username);
        query.setParameter("password", password);

        List Result = query.getResultList();
        ArrayList<utilisateur> results = (ArrayList<utilisateur>) Result;

        return results;
    }

    public utilisateur getUserByEmail(String mail,String Cin){

        Query query = getCurrentSession().getNamedQuery("FindUserToReset");
        query.setParameter("mail", mail);
        query.setParameter("cin", Cin);

        List Result = query.getResultList();
        utilisateur user = (utilisateur) Result.get(0);


        return user;
    }





}
