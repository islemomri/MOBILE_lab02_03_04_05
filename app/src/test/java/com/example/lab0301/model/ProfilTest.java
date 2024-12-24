package com.example.lab0301.model;

import junit.framework.TestCase;

public class ProfilTest extends TestCase {
    //création d'un profil
    private Profil profil = new Profil(67,165,35,0);
    // res img
    private float img= (float)32.18;
    //message
    private String msg="Trop elevé!";

    public void testGetImg() throws Exception{
        assertEquals(img,profil.getImg(),(float)0.1);
    }

    public void testGetMessage() throws Exception{
        assertEquals(msg,profil.getMessage());
    }
}