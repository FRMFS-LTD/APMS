/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import junit.framework.TestCase;

public class clientTest extends TestCase {

    client cl = new client("Adaoui","Samia","T12345");

    public void testGetNom() {
        String expected = "Adaoui";
        String actuel = cl.getNom() ;
        assertEquals(expected,actuel);
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