package dao.Services;

import com.mysql.cj.xdevapi.Client;
import dao.ClientDao;
import dao.UserDao;
import model.client;
import model.utilisateur;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static ClientDao clientDao;

    public ClientService() {
        ClientDao clientDao = new ClientDao() ;
    }

    public void persist(client entity){
        clientDao.openCurrentSessionWithTransaction();
        clientDao.ajout(entity);
        clientDao.closeCurrentSessionWithTransaction();
    }

    public void update(client Entity){
        clientDao.openCurrentSessionWithTransaction();
        clientDao.modifier(Entity);
        clientDao.closeCurrentSessionWithTransaction();
    }

    public client findById(int id){
        clientDao.openCurrentSession();
        client user = clientDao.findById(id);
        clientDao.closeCurrentSession();
        return user ;
    }

    public void supprimer(int id){
        clientDao.openCurrentSessionWithTransaction();
        client cl = clientDao.findById(id);
        clientDao.supprimer(cl);
        clientDao.closeCurrentSessionWithTransaction();
    }

    public List<client> findAll(){
        clientDao.openCurrentSession();
        List<client> tests = clientDao.findAll();
        clientDao.closeCurrentSession();
        return tests;
    }

    public void deleteAll(){
        clientDao.openCurrentSession();
        clientDao.deleteAll();
        clientDao.closeCurrentSession();
    }
    public void deleteAllWithTransaction(){
        clientDao.openCurrentSessionWithTransaction();
        clientDao.deleteAll();
        clientDao.closeCurrentSessionWithTransaction();

    }

    public ArrayList<client> loggedclient(String nom, String prenom,String cin){
        clientDao.openCurrentSessionWithTransaction();
        ArrayList<client> loggedclient = clientDao.loggedclient(nom,prenom,cin);
        clientDao.closeCurrentSessionWithTransaction();
        return loggedclient;
    }


    public ClientDao clientDao(){
        return clientDao;
    }
}

