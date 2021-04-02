package Ddao.Services;

import Ddao.AbonnementDao;
import Model.Abonnement;

import java.util.List;

public class AbonnementService {
    private static AbonnementDao abonnementDao ;

    public AbonnementService (){
        abonnementDao = new AbonnementDao() ;
    }
    public void add (Abonnement entite){
        abonnementDao.add(entite);
        abonnementDao.openCurrentSessionWithTransaction();
        abonnementDao.closeCurrentSessionWithTransaction();
    }
    public void modifier (Abonnement entite){
        abonnementDao.modifier(entite);
        abonnementDao.openCurrentSessionWithTransaction();
        abonnementDao.closeCurrentSessionWithTransaction();
    }
    public Abonnement findbyId(int id){
        abonnementDao.openCurrentSession();
        Abonnement abonne = abonnementDao.findById(id);
        abonnementDao.closeCurrentSession();
        return abonne ;

    }
    public void remove(int id ){
        abonnementDao.openCurrentSessionWithTransaction();
        Abonnement abonn = abonnementDao.findById(id);
        abonnementDao.remove(abonn);
        abonnementDao.closeCurrentSessionWithTransaction();
    }
    public List<Abonnement> findAll(){
        abonnementDao.openCurrentSession();
        List<Abonnement> liste = abonnementDao.findAll();
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
