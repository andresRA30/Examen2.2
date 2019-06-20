package com.example.contacts.data.db.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import com.example.contacts.data.db.converter.DateConverter;

@Entity(tableName = "people")
@TypeConverters(DateConverter.class)
public class Person {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String edad;
    public String foto;
    public String email;

    @Ignore
    public Person() {
        this.name = "";
        this.edad = "";
        this.foto = "";
        this.email = "";
    }

    public Person(String name, String edad, String foto, String email) {
        this.name = name;
        this.edad = edad;
        this.foto = foto;
        this.email = email;
    }
}