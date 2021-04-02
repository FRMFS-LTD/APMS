package Ddao;

import Ddao.Interfaces.UserDaoInterface;
import Model.Utilisateur;
import org.hibernate.SQLQuery;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends MainDao implements UserDaoInterface<Utilisateur, Id> {

    public UserDao() {
    }



    @Override
    public void persist(Utilisateur entity) {
        getCurrentSession().save(entity);
    }


    public Utilisateur findById(int id){
        Utilisateur user = (Utilisateur) getCurrentSession().get(Utilisateur.class, (Serializable) id);
        return user;
    }


    public void update(Utilisateur entity){
        getCurrentSession().update(entity);

    }

    public void delete(Utilisateur entity){
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Utilisateur> findAll(){
        List<Utilisateur> users_list = (List<Utilisateur>) getCurrentSession().createQuery("from utilisateur").list();
        return users_list;
    }

    public void deleteAll() {
        List<Utilisateur> entityList = findAll();
        for (Utilisateur entity : entityList) {
            delete(entity);
        }
    }

    public ArrayList<Utilisateur> loggedUser(String username, String password){

        String sql = "SELECT * FROM utilisateur WHERE username = :username and password = :password";

        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        query.addEntity(Utilisateur.class);

        query.setParameter("username", username);
        query.setParameter("password", password);

        ArrayList<Utilisateur> results = (ArrayList<Utilisateur>) query.list();

        return results;
    }

}
