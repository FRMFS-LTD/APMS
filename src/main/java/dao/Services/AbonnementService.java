package dao.Services;

import dao.AbonnementDao;
import model.abonnement;

import java.util.List;

public class AbonnementService {
    private static AbonnementDao abonnementDao ;

    public AbonnementService (){
        abonnementDao = new AbonnementDao() ;
    }
    public void persist (abonnement entite){
        abonnementDao.persist(entite);
        abonnementDao.openCurrentSessionWithTransaction();
        abonnementDao.closeCurrentSessionWithTransaction();
    }
    public void update (abonnement entite){
        abonnementDao.update(entite);
        abonnementDao.openCurrentSessionWithTransaction();
        abonnementDao.closeCurrentSessionWithTransaction();
    }
    public abonnement findbyId(int id){
        abonnementDao.openCurrentSession();
        abonnement abonne = abonnementDao.findById(id);
        abonnementDao.closeCurrentSession();
        return abonne ;

    }
    public void delete(int id ){
        abonnementDao.openCurrentSessionWithTransaction();
        abonnement abonn = abonnementDao.findById(id);
        abonnementDao.delete(abonn);
        abonnementDao.closeCurrentSessionWithTransaction();
    }
    public List<abonnement> findAll(){
        abonnementDao.openCurrentSession();
        List<abonnement> liste = abonnementDao.findAll();
        abonnementDao.closeCurrentSession();
        return liste ;
    }
    public void deleteAll(){
        abonnementDao.openCurrentSession();
        abonnementDao.deleteAll();
        abonnementDao.closeCurrentSession();
    }
    public void deleteallwithTransaction(){
        abonnementDao.openCurrentSessionWithTransaction();
        abonnementDao.deleteAll();
        abonnementDao.closeCurrentSessionWithTransaction();
    }
}
