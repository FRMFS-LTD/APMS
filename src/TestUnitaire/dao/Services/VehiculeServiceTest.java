/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;

import dao.VehiculeDao;
import junit.framework.TestCase;
import model.Vehicule;
import org.junit.Test;

public class VehiculeServiceTest extends TestCase {
    //classe pour tester les methodes de vehicule service

    VehiculeService vs = new VehiculeService() ;

   @Test
    public void testFindById() { //tester la methode findbyId sur la base de données
    Vehicule v = vs.findById(1);
    assertEquals("" , v.getMatriucle());//tester l'egalite entre la matricule donné et La matricule sur La base de donnes
    }
}