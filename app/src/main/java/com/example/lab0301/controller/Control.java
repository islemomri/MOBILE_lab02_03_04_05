package com.example.lab0301.controller;

import android.content.Context;

import androidx.annotation.IntegerRes;

import com.example.lab0301.model.AccesLocale;
import com.example.lab0301.model.Profil;
import com.example.lab0301.outils.Serializer;

import java.util.Date;

public final class Control {

    private static Control instance=null;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    private static AccesLocale accesLocale;

    private Control(){
        super();
    }

    /**
     * Création d'une instance
     * @return
     */
    public static final Control getInstance(Context contexte){
       if(Control.instance==null){
           Control.instance=new Control();
           accesLocale = new AccesLocale(contexte);
           profil = accesLocale.recupDernier();
            //recupSerializ(contexte);
       }
       return Control.instance;
    }

    /**
     *
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1=H & 0=F
     */
    public void CreateProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte){
    profil=new Profil(new Date(),poids,taille,age,sexe);
    //Serializer.serialize(nomFic,profil,contexte);
    accesLocale.ajout(profil);

    }


    public float getImg(){
        return profil.getImg();
    }

    public String getMsg(){
        return profil.getMessage();
    }
    /**Récuperation de l'objet sérialisé (le profil)*/
    private static void recupSerializ(Context contexte){
        profil = (Profil) Serializer.deSerialize(nomFic,contexte);
    }

    public Integer getPoids(){
        if(profil == null){
            return null;
        }else {
            return profil.getPoids();
        }
    }

    public Integer getTaille() {
        if (profil == null) {
            return null;
        } else {
            return profil.getTaille();
        }
    }
    public Integer getAge(){
            if(profil == null){
                return null;
            }else {

        return profil.getAge();
    }
        }

    public Integer getSexe(){
            if(profil == null){
                return null;
            }else {
                return profil.getSexe();
        }

    }
}
