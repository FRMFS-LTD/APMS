package dao.interfaces;

import model.client;


import java.util.ArrayList;
import java.util.List;

public interface ClientDaoInterface<T,T1> {
    public void ajout(T entity);

    public void modifier (T Entity);

    public T findById(int id);

    public void supprimer(T entity);

    public List<T> findAll();

    public void deleteAll();

    public ArrayList <client> loggedclient(String nom, String prenom,String cin);
}
