package dao.Services;

import dao.clientDao;
import model.client;

import java.util.List;

public class clientService {
    private static clientDao clientdao;
    public clientService(){
         clientdao =new clientDao();
    }
    public void persist(client Entity){
        clientdao.openCurrentSessionWithTransaction();
        clientdao.persist(Entity);
        clientdao.closeCurrentSessionWithTransaction();
    }
    public void update(client Entity){
        clientdao.openCurrentSessionWithTransaction();
        clientdao.update(Entity);
        clientdao.closeCurrentSessionWithTransaction();
    }
    public void delete(int id){
        clientdao.openCurrentSessionWithTransaction();
        client cl = clientdao.findById(id)  ;
        clientdao.delete(cl);
        clientdao.closeCurrentSessionWithTransaction();
    }
    public client findById(int id){
        clientdao.openCurrentSession();
        client clt= clientdao.findById( id);
        clientdao.closeCurrentSession();
        return clt;
    }
    public List<client> findAll(){
        clientdao.openCurrentSession();
        List<client> listeclient= clientdao.findAll();
        clientdao.closeCurrentSession();
        return listeclient  ;
    }
    public  void deleteAll(){
        clientdao.openCurrentSession();
        clientdao.deleteAll();
        clientdao.closeCurrentSession();
    }
    public void deletewithtransaction(){
        clientdao.openCurrentSessionWithTransaction();
        clientdao.deleteAll();
        clientdao.closeCurrentSessionWithTransaction();
    }
}
