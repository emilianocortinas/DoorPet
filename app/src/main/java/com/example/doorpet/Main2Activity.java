package com.example.doorpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private Button refresh;
    private ImageButton idioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        idioma = findViewById(R.id.imgtbn_idioma);
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);


    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

    }

    //add toast si no ah verificado account by email
    public void iniciarSesion(View view){

        mAuth.signInWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Session is started",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(user);
                            Intent i = new Intent(getApplicationContext(), homescreen.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "User and/or password wrong",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            // ...
                        }

                        // ...
                    }
                });
    }

    public void irAregistro(View view){
        Intent i = new Intent(getApplicationContext(), RegistrarseActivity.class);
        startActivity(i);
    }

    public void idioma(View view){
        Locale localizacion = new Locale("es");
        Locale.setDefault(localizacion);
        Configuration config = new Configuration();
        config.locale = localizacion;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Cambiando el idioma a Español.",
                Toast.LENGTH_SHORT).show();
    }
}
