/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;

import dao.typetarifDao;
import junit.framework.TestCase;
import model.typetarif;

public class typetarifServiceTest extends TestCase {

typetarifService ts = new typetarifService() ;

    public void testFindByObject() {
        typetarif tp = new typetarif("daily",22);
        typetarif typT = ts.findByObject(tp) ;
        assertEquals("daily" , typT.getTypetarif());
    }
}