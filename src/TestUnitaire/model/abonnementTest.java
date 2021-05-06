/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import junit.framework.TestCase;

public class abonnementTest extends TestCase {

abonnement abonne = new abonnement("DOM",12, 2.5F);

    public void testGetIntitule() {
    String expected = "DOM";
    String actuel = abonne.getIntitule() ;
    assertEquals(expected,actuel);
    }

    public void testGetPeriode() {
        int expected = 12 ;
        int actuel = abonne.getPeriode() ;
        assertEquals(expected,actuel);
    }

    public void testGetPrix() {
        float expected = 2.5F;
        float actuel = abonne.getPrix() ;
        assertEquals(expected,actuel);
    }
}