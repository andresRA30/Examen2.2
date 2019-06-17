package com.example.contacts.model;

public class Medida {

    private long id;
    private String fecha;
    private int grasa;
    private int masa;
    private int peso;
    private int edad;

    // constructor
    public Medida() {
    }

    public Medida(String fecha, int grasa, int masa, int peso, int edad) {
        this.fecha = fecha;
        this.grasa = grasa;
        this.masa = masa;
        this.peso = peso;
        this.edad = edad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getGrasa() {
        return grasa;
    }

    public void setGrasa(int grasa) {
        this.grasa = grasa;
    }

    public int getmasa() {
        return masa;
    }

    public void setmasa(int masa) {
        this.masa = masa;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


}
