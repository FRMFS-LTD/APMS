/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;
import junit.framework.TestCase;
import model.abonnement;


public class AbonnementServiceTest extends  TestCase {

    AbonnementService as = new AbonnementService() ;

    public void testFindbyId() {

        abonnement abonne = as.findbyId(1) ;

        assertEquals("autmazert",abonne.getIntitule());
    }
}