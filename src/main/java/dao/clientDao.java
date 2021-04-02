package dao;

import dao.MainDao;
import dao.interfaces.clientDaointerface;
import model.client;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class clientDao extends MainDao implements clientDaointerface<client, Id> {
    @Override
    public void persist(client Entity) {
        getCurrentSession().save(Entity);
    }

    @Override
    public void update(client Entity) {
        getCurrentSession().update(Entity);
    }

    @Override
    public void delete(client Entity) {
        getCurrentSession().delete(Entity);
    }

    @Override
    public client findById(int id) {
        client c =(client) getCurrentSession().get(client.class,(Serializable) id);

        return c;
    }

    @Override
    public List<client> findAll() {
        List<client>  client= (List<client>)  getCurrentSession().createQuery("from client").list();
        return client;
    }

    @Override
    public void deleteAll() {
        List<client> c=findAll();
        for (client listeclient : c){
            delete(listeclient);
        }
    }



}
