/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package dao.Services;

import dao.UserDao;
import junit.framework.TestCase;
import model.utilisateur;
import org.junit.Test;

public class UserServiceTest extends TestCase {

    UserService us = new UserService() ;
   @Test
    public void testFindById() {
        utilisateur u = us.findById(1) ;
        assertEquals("Rachid" , u.getNom());
    }
    @Test
    public void testGetUserByEmail() {
        utilisateur u = us.getUserByEmail("rachidboufous32@gmail.com", "ub9746000") ;
        assertEquals("legion11" , u.getUsername());
    }
}