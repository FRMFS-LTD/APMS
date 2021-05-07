/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import junit.framework.TestCase;
import org.junit.Test;

public class VehiculeTest extends TestCase {
    //classe pour tester les methodes du classe vehicule

    client cl = new client("Adaoui","Samia","T12345");
    //constructeur du classe Client

    abonnement abonne = new abonnement("DOM",12, 2.5F);
    //constructeur du classe Abonnement

    Vehicule v = new Vehicule("25457",cl , abonne);
    //constructeur du classe Vehicule

    @Test
    public void testGetMatriucle() {
    assertEquals("25457", v.getMatriucle());
}
    @Test
    public void testGetClient() {
        assertEquals(cl , v.getClient());

    }

    @Test
    public void testGetAbonnement() {
    assertEquals(abonne , v.getAbonnement());
    }//tester l'egalité entre expected et actuel

}