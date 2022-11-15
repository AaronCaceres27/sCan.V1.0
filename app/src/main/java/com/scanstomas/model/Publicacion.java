package com.scanstomas.model;

public class Publicacion {
    String name ,lugar, descripcion,celular,apellido,foto;
    int id;
    public Publicacion(String name, String lugar, String descripcion,String celular,int id,String apellido,String foto) {
        this.id = id;
        this.name = name;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.celular = celular;
        this.apellido = apellido;
        this.foto = foto;
    }

    public Publicacion(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLugar() {return lugar;}

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCelular(String celular) {this.celular = celular;}

    public String getCelular(){return celular;}

    public void setId(Integer id) {this.id = id;}

    public String getFoto() {return foto;}

    public void setFoto(String foto) {this.foto = foto;}

    public Integer id(){return  id;}

    public String getApellido(){return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}
}
