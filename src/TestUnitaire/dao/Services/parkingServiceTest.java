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

    parkingService ps = new parkingService() ;
@Test
    public void testFindById() {
        parking pk = ps.findById(12);
        assertEquals("SDFDG",pk.getVille());
    }

}