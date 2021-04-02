package Ddao.Services;

import Ddao.TypetarifDao;
import Model.Typetarif;

import java.util.List;

public class typetarifService {

    public TypetarifDao typetarifDao;

    public typetarifService() {

        typetarifDao = new TypetarifDao();
    }

    public void persist(Typetarif tyt){
        typetarifDao.openCurrentSessionWithTransaction();
        typetarifDao.persist(tyt);
        typetarifDao.closeCurrentSessionWithTransaction();
    }

    public void update(Typetarif ttyt){
        typetarifDao.openCurrentSessionWithTransaction();
        typetarifDao.update(ttyt);
        typetarifDao.closeCurrentSessionWithTransaction();
    }

    public Typetarif findById(int id){
        typetarifDao.openCurrentSession();
        Typetarif typetarif = typetarifDao.findById(id);
        typetarifDao.closeCurrentSession();
        return typetarif;
    }
    public Typetarif findByObject(Typetarif tyt){
        typetarifDao.openCurrentSession();
        Typetarif typetarif = typetarifDao.findByObject(tyt);
        typetarifDao.closeCurrentSession();
        return typetarif;
    }
    public void delete(int id){
        typetarifDao.openCurrentSessionWithTransaction();
        Typetarif typetarif = typetarifDao.findById(id);
        typetarifDao.delete(typetarif);
        typetarifDao.closeCurrentSessionWithTransaction();
    }

    public void deleteByObject(Typetarif tyt){
        typetarifDao.openCurrentSessionWithTransaction();
        typetarifDao.delete(tyt);
        typetarifDao.closeCurrentSession();
    }

    public List<Typetarif> findAll(){
        typetarifDao.openCurrentSession();
        List<Typetarif> ttyt = typetarifDao.findAll();
        typetarifDao.closeCurrentSession();
        return ttyt;
    }

    public void deleteAll(){
        typetarifDao.openCurrentSession();
        typetarifDao.deleteAll();
        typetarifDao.closeCurrentSession();
    }
    public void deleteAllWithTransaction(){
        typetarifDao.openCurrentSessionWithTransaction();
        typetarifDao.deleteAll();
        typetarifDao.closeCurrentSessionWithTransaction();

    }
    public TypetarifDao typetarifDao(){

        return typetarifDao;
    }

}
