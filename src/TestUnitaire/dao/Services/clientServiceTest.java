/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;


import junit.framework.TestCase;
import model.client;

public class clientServiceTest extends TestCase {

    clientService cs = new clientService() ;

    public void testFindById() {
        client cl = cs.findById(7);
        assertEquals("hgg",cl.getNom());
    }

}