package dao.interfaces;

import dao.MainDao;

import java.util.ArrayList;
import java.util.List;

public interface clientDaointerface<F,F1> {
    public void persist(F Entity);
    public void  update(F Entity);
    public void delete(F Entity);
    public F findById(int id);
    public List<F> findAll();
    public void deleteAll() ;

}
