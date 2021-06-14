package com.example.doorpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.doorpet.adapters.mensajeadapter;
import com.example.doorpet.models.mensaje;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class historial extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private mensajeadapter mAdapter;
    private RecyclerView rw;
    private ArrayList<mensaje> mMensajelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        rw = (RecyclerView)findViewById(R.id.recyclerviewHistorial);
        rw.setLayoutManager(new LinearLayoutManager(this));

        getMensajesFromFirebase();
    }

    private void getMensajesFromFirebase(){
        mDatabase.child("Historial").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.exists()){
                   mMensajelist.clear();
                   for(DataSnapshot ds:snapshot.getChildren()){
                       String Usuario = ds.child("Usuario").getValue().toString();
                       String Fecha = ds.child("Fecha").getValue().toString();
                       String Estado = ds.child("Estado").getValue().toString();
                       mMensajelist.add(new mensaje(Estado,Fecha,Usuario));
                   }
                   Collections.reverse(mMensajelist);
                   mAdapter = new mensajeadapter(mMensajelist,R.layout.mensaje_view);
                   rw.setAdapter(mAdapter);
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
