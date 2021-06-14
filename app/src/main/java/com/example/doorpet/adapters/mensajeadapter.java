package com.example.doorpet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doorpet.R;
import com.example.doorpet.models.mensaje;

import java.util.ArrayList;

public class mensajeadapter extends RecyclerView.Adapter<mensajeadapter.ViewHolder>{


    private int resource;
    private ArrayList<mensaje>mensajeList;

    public mensajeadapter(ArrayList<mensaje>mensajeList,int resource){
        this.mensajeList = mensajeList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        mensaje mensaje = mensajeList.get(position);

        holder.usuario.setText(mensaje.getUsuario());
        holder.estado.setText(mensaje.getEstado());
        holder.fecha.setText(mensaje.getFecha());
    }

    @Override
    public int getItemCount() {

        return mensajeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView usuario;
        private TextView fecha;
        private TextView estado;

        public View view;

        public ViewHolder(View view){
            super(view);
            this.view=view;
            this.usuario = (TextView)view.findViewById(R.id.usuario);
            this.fecha = (TextView)view.findViewById(R.id.fecha);
            this.estado = (TextView)view.findViewById(R.id.estado);
        }
    }

}
