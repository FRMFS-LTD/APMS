
/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;

import dao.UserDao;
import model.utilisateur;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    // after defining all the needed function for database interaction in the DAO
    // here we simply call the function with or without transaction based on the targeted action

    private static UserDao userdao;

    public UserService() {
        userdao = new UserDao();
    }

    public void persist(utilisateur entity){//create new user the action is directly affected to datbase
        userdao.openCurrentSessionWithTransaction();
        userdao.persist(entity);
        userdao.closeCurrentSessionWithTransaction();
    }

    public void update(utilisateur Entity){ //update
        userdao.openCurrentSessionWithTransaction();
        userdao.update(Entity);
        userdao.closeCurrentSessionWithTransaction();
    }

    public utilisateur findById(int id){ // find by id to locate user to delete/update
        userdao.openCurrentSession();
        utilisateur user = userdao.findById(id);
        userdao.closeCurrentSession();
        return user;
    }

    public void delete(int id){ // delete a user using the id
        userdao.openCurrentSessionWithTransaction();
        utilisateur user = userdao.findById(id);
        userdao.delete(user);
        userdao.closeCurrentSessionWithTransaction();
    }


    public List<utilisateur> findAll(){
        userdao.openCurrentSession();
        List<utilisateur> tests = userdao.findAll();
        userdao.closeCurrentSession();
        return tests;
    }

    public void deleteAll(){
        userdao.openCurrentSession();
        userdao.deleteAll();
        userdao.closeCurrentSession();
    }

    public void deleteAllWithTransaction(){
        userdao.openCurrentSessionWithTransaction();
        userdao.deleteAll();
        userdao.closeCurrentSessionWithTransaction();
    }
//calling function for the log in the password reset mechanism
    public ArrayList<utilisateur> loggedUser(String username, String password){
        userdao.openCurrentSession();
        ArrayList<utilisateur> loggedUser = userdao.loggedUser(username,password);
        userdao.closeCurrentSession();
        return loggedUser;
    }

    public utilisateur getUserByEmail(String mail,String Cin){
        userdao.openCurrentSessionWithTransaction();
        utilisateur user = userdao.getUserByEmail(mail,Cin);
        userdao.closeCurrentSessionWithTransaction();
        return user;
    }




}
