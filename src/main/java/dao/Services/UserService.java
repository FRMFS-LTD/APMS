/*
 * Copyright (c) 2021 // programmed by Rachid Boufous
 */

package dao.Services;

import dao.UserDao;
import model.utilisateur;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static UserDao userdao;

    public UserService() {
        userdao = new UserDao();
    }

    public void persist(utilisateur entity){
        userdao.openCurrentSessionWithTransaction();
        userdao.persist(entity);
        userdao.closeCurrentSessionWithTransaction();
    }

    public void update(utilisateur Entity){
        userdao.openCurrentSessionWithTransaction();
        userdao.update(Entity);
        userdao.closeCurrentSessionWithTransaction();
    }

    public utilisateur findById(int id){
        userdao.openCurrentSession();
        utilisateur user = userdao.findById(id);
        userdao.closeCurrentSession();
        return user;
    }

    public void delete(int id){
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

    public ArrayList<utilisateur> loggedUser(String username, String password){
        userdao.openCurrentSessionWithTransaction();
        ArrayList<utilisateur> loggedUser = userdao.loggedUser(username,password);
        userdao.closeCurrentSessionWithTransaction();
        return loggedUser;
    }


}
