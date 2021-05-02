/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.Date;

public class StationnementTest extends TestCase {

    parking pk = new parking("walfa" , 30 , 12  ,"Casa") ;
    client cl = new client("Adaoui","Samia","T12345");
    abonnement abonne = new abonnement("DOM",12, 2.5F);
    Vehicule v = new Vehicule("25457",cl , abonne);
    typetarif tp = new typetarif("tout",123.5F);
    Date DateEntree = new Date(2021, 8, 30);
    Date DateSortie = new Date(2021, 8, 31);
    Stationnement st = new Stationnement(DateEntree,DateSortie,pk,v,tp);

    @Test
    public void testGetDateEntree() {
       assertEquals(DateEntree,st.getDateEntree());
    }

    public void testGetDateSortie() {
     assertEquals(DateSortie , st.getDateSortie());
    }

    public void testGetParking() {
        assertEquals(pk , st.getParking());
    }

    public void testGetVehicule() {
        assertEquals(v , st.getVehicule());
    }

    public void testGetTypetarif() {
        assertEquals(tp , st.getTypetarif());
    }
}