package dao.interfaces;

import java.util.List;

public interface AbonnementDaoInterface <S , S1>{

    public void persist(S entite);
    public void update (S entite);
    public S findById(int id);
    public void delete(S entite);
    public List<S> findAll() ;
    public void deleteAll();
}
