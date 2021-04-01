package dao.interfaces;

import java.util.List;

public interface typetarifdaointerface<F, F1> {

    public void persist(F tyt);

    public void update(F tYt);

    public F findById(int id);

    public void delete(F tyt);

    public List<F> findAll();

    public void deleteAll();
}
