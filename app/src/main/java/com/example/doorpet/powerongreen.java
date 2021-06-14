package com.example.doorpet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class powerongreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Button Abrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powerongreen);

        mAuth = FirebaseAuth.getInstance();

        Abrir = (Button)findViewById(R.id.btn_open);

        Abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.t_opendoor,
                        Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void irAhome(View view){
        Intent i = new Intent(getApplicationContext(), homescreen.class);
        startActivity(i);
    }

    public void irABluethoot(View view){
        Intent i = new Intent(getApplicationContext(), bluethootactivity.class);
        startActivity(i);

    }

    public void irApowerOn(View view){
        Toast.makeText(getApplicationContext(), R.string.t_pon,
                Toast.LENGTH_SHORT).show();
    }

    public void poweoff2(View view){
        Intent i = new Intent(getApplicationContext(), poweroff.class);
        startActivity(i);

    }

    public void logout(View view){
        AlertDialog.Builder alertalogout = new AlertDialog.Builder(powerongreen.this);
        alertalogout.setMessage(R.string.L1)
                .setCancelable(false)
                .setPositiveButton(R.string.L2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        Intent x = new Intent(getApplicationContext(), MainActivity.class);
                        finish();
                        startActivity(x);
                    }
                })
                .setNegativeButton(R.string.L3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog titulo = alertalogout.create();
        titulo.setTitle(R.string.L4);
        titulo.show();
    }
}
