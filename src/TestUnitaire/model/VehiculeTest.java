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
    client cl = new client("Adaoui","Samia","T12345");
    abonnement abonne = new abonnement("DOM",12, 2.5F);
    Vehicule v = new Vehicule("25457",cl , abonne);
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
    }

}