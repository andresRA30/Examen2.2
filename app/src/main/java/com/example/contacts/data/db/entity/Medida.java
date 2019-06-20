package com.example.contacts.data.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.contacts.data.db.converter.DateConverter;

import java.util.Date;

@Entity(tableName = "medida")
@TypeConverters(DateConverter.class)
public class Medida {

    @PrimaryKey(autoGenerate = true)
    public long id;
   public Date fecha;
    public int grasa;
    public int masa;
    public int peso;
    public int edad;
    @Ignore
    public Medida() {
        this.fecha = null;
        this.grasa= 0;
        this.masa= 0;
        this.peso = 0;
        this.edad = 0;
    }

    public Medida(Date fecha,int grasa, int masa, int peso,  int edad) {
        this.fecha = fecha;
        this.grasa = grasa;
        this.masa = masa;
        this.peso = peso;
        this.edad = edad;
    }
}
