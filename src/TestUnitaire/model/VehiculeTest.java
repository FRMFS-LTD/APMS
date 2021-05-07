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

    client cl = new client("Adaoui","Samia","T12345");//constructeur du classe client

    abonnement abonne = new abonnement("DOM",12, 2.5F);//constructeur du classe abonnement

    Vehicule v = new Vehicule("25457",cl , abonne);//constructeur du classe vehicule

    @Test
    public void testGetMatriucle() {
    assertEquals("25457", v.getMatriucle());
}

    @Test
    public void testGetClient() {
        assertEquals(cl , v.getClient()); //tester l'egalité entre expected et actuel

    }

    @Test
    public void testGetAbonnement() {
    assertEquals(abonne , v.getAbonnement());
    }

}