package com.scan.model;

public class Publicacion {
    String datos ,lugar, descripcion;

    public Publicacion(String datos, String lugar, String descripcion) {
        this.datos = datos;
        this.lugar = lugar;
        this.descripcion = descripcion;
    }

    public Publicacion(){
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
