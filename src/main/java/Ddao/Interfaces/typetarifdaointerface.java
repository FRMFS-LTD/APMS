package Ddao.Interfaces;

import Model.Typetarif;

import java.util.List;

public interface typetarifdaointerface<F, F1> {

    public void persist(F tyt);

    public void update(F tYt);

    public F findById(int id);

    public F findByObject(F tyt);

    public void delete(F tyt);

    public void deleteByObject(Typetarif tyt);

    public List<F> findAll();

    public void deleteAll();
}
