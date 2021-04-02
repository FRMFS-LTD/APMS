package dao.interfaces;

import model.utilisateur;

import java.util.ArrayList;
import java.util.List;

public interface UserDaoInterface<T, T1> {

    public void persist(T entity);

    public void update(T entity);

    public T findById(int id);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();

    public ArrayList<utilisateur> loggedUser(String userName, String password);

}
