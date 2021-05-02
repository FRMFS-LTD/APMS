/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import junit.framework.TestCase;

public class typetarifTest extends TestCase {

    typetarif typ = new typetarif("ilimite" , 152.685F) ;
    public void testGetTypetarif() {
        assertEquals("ilimite" , typ.getTypetarif());
    }

    public void testGetPrix() {
        assertEquals(152.685F , typ.getPrix());
    }
}
