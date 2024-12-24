package com.example.lab0301.vue;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab0301.R;
import com.example.lab0301.controller.Control;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }
    //propriétes
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView labelImg;
    private ImageView imgSmiley;
    private Control controle;



/**
* Initialisation des liens avec les objets graphiques*/
    private void init(){
        txtPoids = (EditText)findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        labelImg =(TextView) findViewById(R.id.labelImg);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        this.controle= Control.getInstance(this);
        ecouteCalul();
        recupProfil();
    }

    /**50
    Ecoute événement sur boutton calcul
     je recuperer l'objet graphique de boutton puis japplique la méthode setOnClickListener
     qui permet d'initialiser un ecouteur sur le boutton
     */
    private void ecouteCalul(){
        ((Button) findViewById(R.id.btnCalculer)).setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v){
           /** Toast.makeText(MainActivity.this, "Hell o world ", Toast.LENGTH_SHORT).show();
            Log.d("message","click ok sur le bouton calcul *******");*/
           Integer poids = 0;
           Integer taille = 0;
           Integer age = 0;
           Integer sexe = 0;
    //Récuperation des donneés saisies
           try {
               poids = Integer.parseInt(txtPoids.getText().toString());
               taille = Integer.parseInt(txtTaille.getText().toString());
               age = Integer.parseInt(txtAge.getText().toString());
           }catch (Exception e){e.printStackTrace();};
           if(rdHomme.isChecked()){
               sexe = 1;
           }
           if(poids ==0 || taille ==0 || age == 0){
               Toast.makeText(MainActivity.this, "veuillez saisir les champs ", Toast.LENGTH_SHORT).show();
           }else{
               afficherResultat(poids,taille,age,sexe);
           }
        }
        });
    }
    // Affichage de l'IMG ,du message et de l'image
    private void afficherResultat(Integer p, Integer t, Integer a, Integer s){
        //création d'un profil
        this.controle.CreateProfil(p,t,a,s,this);
        float img = this.controle.getImg();
        String message = this.controle.getMsg();

        //affichage
        if(message == "normal"){
            imgSmiley.setImageResource(R.drawable.normal);
            labelImg.setTextColor(Color.GREEN);
        }else{
            if(message == "Trop faible!"){
                imgSmiley.setImageResource(R.drawable.sad);
                labelImg.setTextColor(Color.RED);
            }else{
                imgSmiley.setImageResource(R.drawable.m);
                labelImg.setTextColor(Color.RED);
            }
        }
        labelImg.setText(String.format("%.1f",img)+ " : IMG"+message);
    }

    public void recupProfil(){
        if(controle.getPoids() !=null){
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());
            rdFemme.setChecked(true);
            if(controle.getSexe()==1){
                rdHomme.setChecked(true);
            }
            //Simule le click sur le bouton calcul
            ((Button)findViewById(R.id.btnCalculer)).performClick() ;
        }
    }
}