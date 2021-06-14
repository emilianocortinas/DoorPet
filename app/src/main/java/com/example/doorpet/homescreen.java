package com.example.doorpet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.Inet4Address;

public class homescreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextView Email;
    Button Historial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);


        mAuth = FirebaseAuth.getInstance();

        Email = (TextView)findViewById(R.id.tv_email);
        Historial = (Button)findViewById(R.id.btn_historial);


    }

    @Override
    protected void onResume(){
        super.onResume();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if(mUser!=null){
            Email.setText(mUser.getEmail());
        }
    }

    public void irHistorial(View view){
        Intent i = new Intent(getApplicationContext(), historial.class);
        startActivity(i);
    }

    public void irAhome(View view){
        Toast.makeText(getApplicationContext(), R.string.t_home,
                Toast.LENGTH_SHORT).show();
    }
    public void irABluethoot(View view){
        Intent i = new Intent(getApplicationContext(), bluethootactivity.class);
        startActivity(i);
    }
    public void irApowerOn(View view){
        Intent i = new Intent(getApplicationContext(), poweron.class);
        startActivity(i);
    }
    public void irApowerOff(View view){
        Intent i = new Intent(getApplicationContext(),poweroff.class);
        startActivity(i);
    }
    public void irAtuto(View view){
        Intent i = new Intent(getApplicationContext(),tutovideo.class);
        finish();
        startActivity(i);
    }
    public void logout(View view){

       AlertDialog.Builder alertalogout = new AlertDialog.Builder(homescreen.this);
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
