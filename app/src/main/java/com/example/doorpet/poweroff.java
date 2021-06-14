package com.example.doorpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.bluetoothjhr.BluetoothJhr;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class poweroff extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference mRootReference;

    BluetoothJhr bluetoothJhr;

    Button Cerrar;
    TextView tv_displaystatus;
    String Nombre=null;
    String FYH = null;
    String Estado = "Cerrado - Closed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poweroff);

        mAuth = FirebaseAuth.getInstance();
        mRootReference = FirebaseDatabase.getInstance().getReference();

        Cerrar = (Button)findViewById(R.id.btn_close);
        tv_displaystatus = (TextView)findViewById(R.id.tv_displaystatus);
        bluetoothJhr = new BluetoothJhr(poweroffred.class,this);

        Cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CerrarTexto = "180";
                bluetoothJhr.Tx("c"+CerrarTexto);
                tv_displaystatus.setText(R.string.tv_statusoff);

                Map<String,Object> datoshistorial = new HashMap<>();
                datoshistorial.put("Usuario",Nombre);
                datoshistorial.put("Fecha",FYH);
                datoshistorial.put("Estado",Estado);
                mRootReference.child("Historial").push().setValue(datoshistorial);

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

    public void irApowerOff(View view){
        Intent i = new Intent(getApplicationContext(),poweron.class);
        startActivity(i);
    }

    public void irApowerOn(View view){
        Intent i = new Intent(getApplicationContext(),poweron.class);
        startActivity(i);
    }

    public void logout(View view){
        mAuth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onResume(){
        super.onResume();
        bluetoothJhr.ConectaBluetooth();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            Nombre = mUser.getEmail();
        }
        FYH = new Date().toString();
    }

    @Override
    public void onPause(){
        super.onPause();
        bluetoothJhr.CierraConexion();
    }



}
