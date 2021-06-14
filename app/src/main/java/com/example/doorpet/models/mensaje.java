package com.example.doorpet.models;

public class mensaje{

    private String Estado;
    private String Fecha;
    private String Usuario;

    public mensaje(){}

    public mensaje(String Estado, String Fecha, String Usuario){
        this.Estado = Estado;
        this.Fecha = Fecha;
        this.Usuario = Usuario;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
}
