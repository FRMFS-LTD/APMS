package dao;

import com.mysql.cj.xdevapi.Client;
import dao.interfaces.ClientDaoInterface;
import dao.interfaces.UserDaoInterface;
import model.client;
import model.utilisateur;
import org.hibernate.SQLQuery;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientDao extends MainDao implements ClientDaoInterface<client, Id> {

    public ClientDao() {
    }


    @Override
    public void ajout(client entity) {
        getCurrentSession().save(entity);
    }




    public client findById(int id){
        client c = (client) getCurrentSession().get(client.class, (Serializable) id);
        return c ;
    }

    public void modifier(client entity){
        getCurrentSession().update(entity);

    }

    public void supprimer(client entity){
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<client> findAll(){
        List<client> users_list = (List<client>) getCurrentSession().createQuery("from client").list();
        return users_list;
    }

    public void deleteAll() {
        List<client> entityList = findAll();
        for (client entity : entityList) {
            supprimer(entity);
        }
    }

    @Override
    public ArrayList<client> loggedclient(String nom, String prenom,String cin) {
        return null;
    }

    public ArrayList<client> loggedUser(String nom, String prénom, String cin){
        String sql = "SELECT * FROM Client";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.addEntity(client.class);
        query.setParameter("nom", nom);
        query.setParameter("prénom", prénom);
        query.setParameter("cin", cin);


        ArrayList<client> results = (ArrayList<client>) query.list();
        return results;
    }

}


