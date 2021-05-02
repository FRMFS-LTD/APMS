/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class StationnementTest extends TestCase {

    parking pk = new parking("walfa" , 30 , 12  ,"Casa") ;
    client cl = new client("Adaoui","Samia","T12345");
    abonnement abonne = new abonnement("DOM",12, 2.5F);
    Vehicule v = new Vehicule("25457",cl , abonne);
    typetarif tp = new typetarif("tout",123.5F);

    @Test
    public void testGetDateEntree() {


    }

    public void testGetDateSortie() {
    }

    public void testGetParking() {
    }

    public void testGetVehicule() {
    }

    public void testGetTypetarif() {
    }
}