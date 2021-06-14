package com.example.doorpet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.bluetoothjhr.BluetoothJhr;
import com.google.firebase.auth.FirebaseAuth;

public class bluethootactivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    ListView ListaDispositivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluethootactivity);

        mAuth = FirebaseAuth.getInstance();

        ListaDispositivos =  (ListView)findViewById(R.id.ListaDispositivos);

        final BluetoothJhr bluetoothJhr = new BluetoothJhr(this, ListaDispositivos);
        bluetoothJhr.EncenderBluetooth();

        ListaDispositivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bluetoothJhr.Disp_Seleccionado(view,position, TransitorBluethoot.class);
            }
        });
    }

    public void ConfigBluetooth (View view){
        startActivityForResult(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS),0);
    }

    public void irABluethoot(View view){
        Toast.makeText(getApplicationContext(), R.string.t_bluetooth,
                Toast.LENGTH_SHORT).show();
    }

    public void irAHome(View view){
        Intent i = new Intent(getApplicationContext(), homescreen.class);
        startActivity(i);
    }

    public void logout(View view){
        AlertDialog.Builder alertalogout = new AlertDialog.Builder(bluethootactivity.this);
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

    public void irApowerOn(View view){
        Intent i = new Intent(getApplicationContext(), poweron.class);
        startActivity(i);
    }

    public void irApowerOff(View view){
        Intent i = new Intent(getApplicationContext(),poweroff.class);
        startActivity(i);
    }
}
