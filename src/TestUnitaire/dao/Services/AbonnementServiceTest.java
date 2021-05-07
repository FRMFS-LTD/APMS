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
  //classe pour tester les methodes d'abonnement service
    AbonnementService as = new AbonnementService() ;

    public void testFindbyId() {
    //tester la methode findbyId sur la base de données
        abonnement abonne = as.findbyId(1) ;

        assertEquals("autmazert",abonne.getIntitule());//tester l'egalite entre l'intitule donné et L'intitule sur La base de donnes
    }
}