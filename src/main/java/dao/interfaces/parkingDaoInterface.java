package dao.interfaces;
/*
 * Copyright (c) 2021.
 * programmed by Fadoua Abdoulmoulah.
 * for FRMFS-ltd organisation
 *
 */
import java.util.List;

public interface parkingDaoInterface <P, P1>{
    public void persist(P Pg);

    public void update(P entity);

    public P findById(int id);

    public void delete(P entity);

    public List<P> findAll();

    public void deleteAll();
}
