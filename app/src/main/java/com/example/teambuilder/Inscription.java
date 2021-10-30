package com.example.teambuilder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Inscription extends AppCompatActivity {
    public EditText mNom, mPrenom, mEmail, mEmail_Confirmation, mMot_Passe, mMot_Passe_Confirmation;
    public Button mInscription;
    FirebaseAuth mFireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        mNom = (EditText)findViewById(R.id.Nom);
        mPrenom = findViewById(R.id.Prenom);
        mEmail = (EditText)findViewById(R.id.Email);
        mEmail_Confirmation = findViewById(R.id.Email_confirmation);
        mMot_Passe=(EditText)findViewById(R.id.Mot_passe);
        mMot_Passe_Confirmation=findViewById(R.id.Mot_passe_confirmation);
        mInscription = (Button) findViewById(R.id.Inscription);

        mFireBaseAuth = FirebaseAuth.getInstance();



        mInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = mEmail.getText().toString();
                String Mot_Passe = mMot_Passe.getText().toString();



                if(TextUtils.isEmpty(Email)){
                    mEmail.setError("Veuillez remplir Email");
                    return;
                }

                if(TextUtils.isEmpty(Mot_Passe)){
                    mMot_Passe.setError("Veuillez remplir Mot_Passe");
                    return;
                }
                            if(Mot_Passe.length()<6){
                                mMot_Passe.setError("le mot de passe doit contenir au moins 6 caractères");
                                return;
                            }

                mFireBaseAuth.createUserWithEmailAndPassword(Email,Mot_Passe).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Inscription.this,"Utilisateur créer",Toast.LENGTH_SHORT);
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                                        Toast.makeText(Inscription.this,"Ereeur ! " +task.getException().getMessage() ,Toast.LENGTH_SHORT);

                        }

                    }
                });








            }
        });


    }
}