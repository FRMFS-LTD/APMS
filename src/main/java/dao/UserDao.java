package dao;

import dao.interfaces.UserDaoInterface;
import model.utilisateur;
import org.hibernate.SQLQuery;

import javax.persistence.Id;
import java.io.Serializable;
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

        String sql = "SELECT * FROM utilisateur WHERE username = :username and password = :password";

        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        query.addEntity(utilisateur.class);

        query.setParameter("username", username);
        query.setParameter("password", password);

        ArrayList<utilisateur> results = (ArrayList<utilisateur>) query.list();

        return results;
    }

}
