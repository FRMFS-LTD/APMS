/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;


import junit.framework.TestCase;
import model.client;

public class clientServiceTest extends TestCase {
    //classe pour tester les methodes de client service

    clientService cs = new clientService() ;

    public void testFindById() { //tester la methode findbyId sur la base de données
        client cl = cs.findById(7);
        assertEquals("hgg",cl.getNom()); //tester l'egalite entre le nom donné et Le nom sur La base de donnes
    }

}