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


public class StationnementTest extends TestCase {
    //classe pour tester les methodes du classe Stationnement

    parking pk = new parking("walfa" , 30 , 12  ,"Casa") ;//constructeur du classe parking

    client cl = new client("Adaoui","Samia","T12345");//constructeur du classe Client

    abonnement abonne = new abonnement("DOM",12, 2.5F);//constructeur du classe abonnement

    Vehicule v = new Vehicule("25457",cl , abonne);//constructeur du classe vehicule

    typetarif tp = new typetarif("tout",123.5F); //constructeur du classe TypeTarif

    LocalDate DateSortie = LocalDate.of(2021,05,16) ;
     LocalDate DateEntree = LocalDate.of(2021,05, 15) ;
    Stationnement st = new Stationnement(DateEntree,DateSortie,pk,v,tp);
    //constructeur du classe stationnement

    @Test
    public void testGetDateEntree() {
       assertEquals(DateEntree,st.getDateEntree());
    }

    public void testGetDateSortie() {
     assertEquals(DateSortie , st.getDateSortie());
    } //tester l'egalité entre expected et actuel

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