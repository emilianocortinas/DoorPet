package com.example.doorpet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.bluetoothjhr.BluetoothJhr;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


public class poweron extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference mRootReference;

    BluetoothJhr bluetoothJhr;

    Button Abrir;
    ImageView Home;
    TextView tv_displaystatus;
    String Nombre=null;
    String FYH = null;
    String Estado = "Abierto - Open";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poweron);

        mAuth = FirebaseAuth.getInstance();
        mRootReference = FirebaseDatabase.getInstance().getReference();

        Abrir = (Button)findViewById(R.id.btn_open);
        Home = (ImageView)findViewById(R.id.iv_home);
        tv_displaystatus = (TextView)findViewById(R.id.tv_displaystatus);
        bluetoothJhr = new BluetoothJhr(powerongreen.class,this);

        Abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String AbrirTexto = "0";
                bluetoothJhr.Tx("o"+AbrirTexto);
                tv_displaystatus.setText(R.string.tv_statuson2);
               // Toast.makeText(getApplicationContext(), AbrirTexto,
                        //Toast.LENGTH_SHORT).show();

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

    public void irApowerOn(View view){
        Intent i = new Intent(getApplicationContext(), poweroff.class);
        startActivity(i);
    }

    public void poweoff2(View view){
        Intent i = new Intent(getApplicationContext(), poweroff.class);
        startActivity(i);
    }

    public void logout(View view){
        mAuth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        bluetoothJhr.ConectaBluetooth();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            Nombre = mUser.getEmail();
        }
        FYH = new Date().toString();

    }

    @Override
    protected void onPause(){
        super.onPause();
        bluetoothJhr.CierraConexion();
    }


}
