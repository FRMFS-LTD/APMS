/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import junit.framework.TestCase;

public class clientTest extends TestCase {
    //classe pour tester les methodes du classe Client

    client cl = new client("Adaoui","Samia","T12345");
    //constructeur du classe Client

    public void testGetNom() {
        String expected = "Adaoui";
        String actuel = cl.getNom() ;
        assertEquals(expected,actuel);//tester l'egalité entre expected et actuel
    }

    public void testGetPrenom() {
        String expected = "Samia";
        String actuel = cl.getPrenom() ;
        assertEquals(expected,actuel);
    }

    public void testGetCin() {
        String expected = "T12345";
        String actuel = cl.getCin() ;
        assertEquals(expected,actuel);
    }
}