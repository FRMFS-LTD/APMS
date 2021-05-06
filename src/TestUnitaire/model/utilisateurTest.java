/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package model;

import junit.framework.TestCase;

public class utilisateurTest extends TestCase {
    private Boolean is_admin;

    utilisateur u = new utilisateur("tono","kamil","T45784","065897412","rachidboufous32@gmail.com","tonokamil","123",is_admin);

    public void testGetNom() {
        assertEquals("tono" , u.getNom());
    }

    public void testGetPrenom() {
        assertEquals("kamil" , u.getPrenom());
    }

    public void testGetCin() {
        assertEquals("T45784" , u.getCin());
    }

    public void testGetTel() {
        assertEquals("065897412" , u.getTel());
    }

    public void testGetMail() {

        assertEquals("rachidboufous32@gmail.com" , u.getMail());
    }

    public void testGetUsername() {
        assertEquals("tonokamil" , u.getUsername());
    }

    public void testGetPassword() {
        assertEquals("123" , u.getPassword());
    }

    public void testGetIs_admin() {
        assertEquals(is_admin , u.getIs_admin());
    }
}