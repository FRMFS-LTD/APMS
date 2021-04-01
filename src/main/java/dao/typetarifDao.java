package dao;

import dao.interfaces.typetarifdaointerface;
import model.typetarif;
import model.utilisateur;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class typetarifDao extends MainDao implements typetarifdaointerface<typetarif, Id> {

    public typetarifDao(){

    }

    public void persist(typetarif tyt){
        getCurrentSession().save(tyt);
    }
    public typetarif findById(int id){
        typetarif tYt = (typetarif)getCurrentSession().get(typetarif.class, (Serializable) id);
        return tYt;
    }

    @Override
    public typetarif findByObject(typetarif tyt) {
        return tyt;
    }


    public void update(typetarif tyt){
        getCurrentSession().update(tyt);

    }

    public void delete(typetarif tyt){
        getCurrentSession().delete(tyt);
    }

    @Override
    public void deleteByObject(typetarif tyt) {
        getCurrentSession().delete(tyt);
    }

    @SuppressWarnings("unchecked")
    public List<typetarif> findAll(){
        List<typetarif> typetarifs_list = (List<typetarif>) getCurrentSession().createQuery("from typetarif ").list();
        return typetarifs_list;
    }

    public void deleteAll() {
        List<typetarif> typetarifs_List = findAll();
        for (typetarif tyt : typetarifs_List) {
            delete(tyt);
        }
    }
}
