package dao.interfaces;

import java.util.List;

public interface AbonnementDaoInterface <S , S1>{

    public void add(S entite);
    public void modifier (S entite);
    public S findById(int id);
    public void remove(S entite);
    public List<S> findAll() ;
    public void removeAll();
}
