package dao.Services;

import dao.AbonnementDao;
import model.abonnement;

import java.util.List;

public class AbonnementService {
    private static AbonnementDao abonnementDao ;

    public AbonnementService (){
        abonnementDao = new AbonnementDao() ;
    }
    public void add (abonnement entite){
        abonnementDao.add(entite);
        abonnementDao.openCurrentSessionWithTransaction();
        abonnementDao.closeCurrentSessionWithTransaction();
    }
    public void modifier (abonnement entite){
        abonnementDao.modifier(entite);
        abonnementDao.openCurrentSessionWithTransaction();
        abonnementDao.closeCurrentSessionWithTransaction();
    }
    public abonnement findbyId(int id){
        abonnementDao.openCurrentSession();
        abonnement abonne = abonnementDao.findById(id);
        abonnementDao.closeCurrentSession();
        return abonne ;

    }
    public void remove(int id ){
        abonnementDao.openCurrentSessionWithTransaction();
        abonnement abonn = abonnementDao.findById(id);
        abonnementDao.remove(abonn);
        abonnementDao.closeCurrentSessionWithTransaction();
    }
    public List<abonnement> findAll(){
        abonnementDao.openCurrentSession();
        List<abonnement> liste = abonnementDao.findAll();
        abonnementDao.closeCurrentSession();
        return liste ;
    }
    public void removeAll(){
        abonnementDao.openCurrentSession();
        abonnementDao.removeAll();
        abonnementDao.closeCurrentSession();
    }
    public void removeallwithTransaction(){
        abonnementDao.openCurrentSessionWithTransaction();
        abonnementDao.removeAll();
        abonnementDao.closeCurrentSessionWithTransaction();
    }
}
