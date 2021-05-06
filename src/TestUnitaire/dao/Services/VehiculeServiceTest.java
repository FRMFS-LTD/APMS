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

    VehiculeService vs = new VehiculeService() ;

   @Test
    public void testFindById() {
    Vehicule v = vs.findById(1);
    assertEquals("" , v.getMatriucle());
    }
}