package com.example.lab0301.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab0301.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocale {
    //propriétés
    private String  nomBase = "dbCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public AccesLocale(Context context){
        accesBD = new MySQLiteOpenHelper(context,nomBase,null,versionBase);
    }

    public void ajout(Profil profil){
        bd = accesBD.getWritableDatabase();
        String req = "insert into profil (datemesure,poids,taille,age,sexe) values";
        req += "(\""+profil.getDateMesure()+"\","+profil.getPoids()+","+profil.getTaille()+","+profil.getAge()+","+profil.getSexe()+")";
        bd.execSQL(req);
    }

    /**
     * récuperation du dernier profil de la BD
     * @return
     */
    public Profil recupDernier(){
        bd = accesBD.getReadableDatabase();
        Profil profil = null;
        String req = "select * from profil;";
        Cursor cursor = bd.rawQuery(req,null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            Date date = new Date();
            Integer poids = cursor.getInt(1);
            Integer taille = cursor.getInt(2);
            Integer age = cursor.getInt(3);
            Integer sexe = cursor.getInt(4);
            profil = new Profil(date,poids,taille,age,sexe);
        }
        cursor.close();
        return profil;
    }
}
