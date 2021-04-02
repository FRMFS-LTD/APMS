package dao.Services;

import dao.UserDao;
import dao.typetarifDao;
import model.typetarif;
import model.utilisateur;

import java.util.List;

public class typetarifService {

    public dao.typetarifDao typetarifDao;

    public typetarifService() {

        typetarifDao = new typetarifDao();
    }

    public void persist(typetarif tyt){
        typetarifDao.openCurrentSessionWithTransaction();
        typetarifDao.persist(tyt);
        typetarifDao.closeCurrentSessionWithTransaction();
    }

    public void update(typetarif ttyt){
        typetarifDao.openCurrentSessionWithTransaction();
        typetarifDao.update(ttyt);
        typetarifDao.closeCurrentSessionWithTransaction();
    }

    public typetarif findById(int id){
        typetarifDao.openCurrentSession();
        typetarif typetarif = typetarifDao.findById(id);
        typetarifDao.closeCurrentSession();
        return typetarif;
    }
    public typetarif findByObject(typetarif tyt){
        typetarifDao.openCurrentSession();
        typetarif typetarif = typetarifDao.findByObject(tyt);
        typetarifDao.closeCurrentSession();
        return typetarif;
    }
    public void delete(int id){
        typetarifDao.openCurrentSessionWithTransaction();
        typetarif typetarif = typetarifDao.findById(id);
        typetarifDao.delete(typetarif);
        typetarifDao.closeCurrentSessionWithTransaction();
    }

    public void deleteByObject(typetarif tyt){
        typetarifDao.openCurrentSessionWithTransaction();
        typetarifDao.delete(tyt);
        typetarifDao.closeCurrentSession();
    }

    public List<typetarif> findAll(){
        typetarifDao.openCurrentSession();
        List<typetarif> ttyt = typetarifDao.findAll();
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
    public typetarifDao typetarifDao(){

        return typetarifDao;
    }

}
