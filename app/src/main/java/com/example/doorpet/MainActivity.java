package com.example.doorpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private ImageButton idioma;
    private Button refresh;
    String language = "es";
    int a=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                            Toast.makeText(getApplicationContext(), "Sesion iniciada correctamente.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(user);
                            Intent i = new Intent(getApplicationContext(), homescreen.class);
                            finish();
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectas.",
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
        finish();
        startActivity(i);
    }

    public void idioma(View view){
        Locale localizacion = new Locale("en");
        Locale.setDefault(localizacion);
        Configuration config = new Configuration();
        config.locale = localizacion;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
        finish();
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Changing Language to English.",
                Toast.LENGTH_SHORT).show();
    }

}
