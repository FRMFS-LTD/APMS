package Ddao;

import Ddao.Interfaces.typetarifdaointerface;
import Model.Typetarif;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

public class TypetarifDao extends MainDao implements typetarifdaointerface<Typetarif, Id> {

    public TypetarifDao(){

    }

    public void persist(Typetarif tyt){
        getCurrentSession().save(tyt);
    }
    public Typetarif findById(int id){
        Typetarif SRT = (Typetarif)getCurrentSession().get(Typetarif.class, (Serializable) id);
        return SRT;
    }

    @Override
    public Typetarif findByObject(Typetarif tyt) {
        return tyt;
    }


    public void update(Typetarif tyt){
        getCurrentSession().update(tyt);

    }

    public void delete(Typetarif tyt){
        getCurrentSession().delete(tyt);
    }

    @Override
    public void deleteByObject(Typetarif tyt) {
        getCurrentSession().delete(tyt);
    }

    @SuppressWarnings("unchecked")
    public List<Typetarif> findAll(){
        List<Typetarif> typetarifs_list = (List<Typetarif>) getCurrentSession().createQuery("from typetarif ").list();
        return typetarifs_list;
    }

    public void deleteAll() {
        List<Typetarif> typetarifs_List = findAll();
        for (Typetarif tyt : typetarifs_List) {
            delete(tyt);
        }
    }
}
