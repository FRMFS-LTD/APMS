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

    StationnementService ss = new StationnementService() ;

    @Test
    public void TestGetFindbyId(){

        Stationnement st = ss.findById(1) ;
        assertEquals("" , st.getVehicule());
    }

}