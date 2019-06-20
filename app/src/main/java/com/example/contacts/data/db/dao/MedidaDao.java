package com.example.contacts.data.db.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.contacts.data.db.entity.Medida;
import com.example.contacts.data.db.entity.Person;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface MedidaDao {


    @Query("SELECT * FROM medida ORDER BY fecha ASC")
    LiveData<List<Medida>> findAllMedida();

    @Query("SELECT * FROM medida")
    List<Medida> getAllChannels();

    @Query("SELECT * FROM medida WHERE id=:id")
    Medida findMedidaById(String id);

    @Query("SELECT * FROM medida WHERE id=:id")
    Medida findMedida(long id);

    @Insert(onConflict = IGNORE)
    long insertMedida(Medida medida);

    @Update
    int updateMedida(Medida medida);

    @Update
    void updateMedida(List<Medida> medida);

    @Delete
    void deleteMedida(Medida medida);

    @Query("DELETE FROM medida")
    void deleteAll();
}