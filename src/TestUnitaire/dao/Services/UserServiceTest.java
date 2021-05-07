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
    //classe pour tester les methodes de Utilisateur service
    UserService us = new UserService() ;
   @Test
    public void testFindById() {
       //tester la methode findbyId sur la base de données
        utilisateur u = us.findById(1) ;
        assertEquals("Rachid" , u.getNom());//tester l'egalite entre le nom donné et le UserName sur La base de donnes
    }
    @Test
    public void testGetUserByEmail() {
        //tester la methode findbyEmail sur la base de données
        utilisateur u = us.getUserByEmail("rachidboufous32@gmail.com", "ub9746000") ;
        assertEquals("legion11" , u.getUsername()); //tester l'egalite entre le nom donné et le UserName sur La base de donnes
    }
}