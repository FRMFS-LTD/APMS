package dao.interfaces;

import model.Vehicule;

import java.util.List;

public interface VehiculeDaoInterface<T, T1> {


    public void persist(T entity);

    public Vehicule findById(int id);

    public void update(T entity);

    public void delete(T entity);

    public List<Vehicule> findAll();

    public void deleteAll();
}
