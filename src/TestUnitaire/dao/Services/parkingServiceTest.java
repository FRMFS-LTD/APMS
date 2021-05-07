/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;

import dao.parkingDao;
import junit.framework.TestCase;
import model.client;
import model.parking;
import org.junit.Test;

public class parkingServiceTest extends TestCase {
    //classe pour tester les methodes de Parking service
    parkingService ps = new parkingService() ;
@Test
    public void testFindById() {
    //tester la methode findbyId sur la base de données
        parking pk = ps.findById(12);
        assertEquals("SDFDG",pk.getVille());//tester l'egalite entre la ville  donné et le Ville sur La base de donnes
    }

}