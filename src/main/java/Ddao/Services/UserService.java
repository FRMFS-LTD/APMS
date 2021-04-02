package Ddao.Services;

import Ddao.UserDao;
import Model.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static UserDao userdao;

    public UserService() {
        userdao = new UserDao();
    }

    public void persist(Utilisateur entity){
        userdao.openCurrentSessionWithTransaction();
        userdao.persist(entity);
        userdao.closeCurrentSessionWithTransaction();
    }

    public void update(Utilisateur Entity){
        userdao.openCurrentSessionWithTransaction();
        userdao.update(Entity);
        userdao.closeCurrentSessionWithTransaction();
    }

    public Utilisateur findById(int id){
        userdao.openCurrentSession();
        Utilisateur user = userdao.findById(id);
        userdao.closeCurrentSession();
        return user;
    }

    public void delete(int id){
        userdao.openCurrentSessionWithTransaction();
        Utilisateur user = userdao.findById(id);
        userdao.delete(user);
        userdao.closeCurrentSessionWithTransaction();
    }


    public List<Utilisateur> findAll(){
        userdao.openCurrentSession();
        List<Utilisateur> tests = userdao.findAll();
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

    public ArrayList<Utilisateur> loggedUser(String username, String password){
        userdao.openCurrentSessionWithTransaction();
        ArrayList<Utilisateur> loggedUser = userdao.loggedUser(username,password);
        userdao.closeCurrentSessionWithTransaction();
        return loggedUser;
    }


}
