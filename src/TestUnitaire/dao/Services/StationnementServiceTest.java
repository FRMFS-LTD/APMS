/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;
import dao.StationnementDao;
import junit.framework.TestCase;
import model.Stationnement;
import org.junit.Test;

public class StationnementServiceTest extends TestCase {
    //classe pour tester les methodes de Stationnement service
    StationnementService ss = new StationnementService() ;

    @Test
    public void TestGetFindbyId(){ //tester la methode findbyId sur la base de données

        Stationnement st = ss.findById(1) ;
        assertEquals("" , st.getVehicule());//tester l'egalite entre la matricule donné et La matricule sur La base de donnes
    }

}