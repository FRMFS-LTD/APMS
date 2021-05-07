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
    //classe pour tester les methodes de typeTarif service
typetarifService ts = new typetarifService() ;

    public void testFindByObject() { //tester la methode findbyObject sur la base de données
        typetarif tp = new typetarif("daily",22);
        typetarif typT = ts.findByObject(tp) ;
        assertEquals("daily" , typT.getTypetarif());//tester l'egalite entre le typeTarif donné et Le typeTarif sur La base de donnes
    }
}