/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import junit.framework.TestCase;

public class parkingTest extends TestCase {

    parking pk = new parking("walfa" , 30 , 12  ,"Casa") ;

    public void testGetAddress() {
        String expected = "walfa";
        String actuel = pk.getAddress();
        assertEquals(expected,actuel);
    }

    public void testGetNbplace() {
        int expected = 30;
        int actuel = pk.getNbplace();
        assertEquals(expected,actuel);
    }

    public void testGetNbplacelibre() {
        int expected = 12;
        int actuel = pk.getNbplacelibre();
        assertEquals(expected,actuel);
    }

    public void testGetVille() {
        String expected = "Casa";
        String actuel = pk.getVille() ;
        assertEquals(expected,actuel);
    }
}