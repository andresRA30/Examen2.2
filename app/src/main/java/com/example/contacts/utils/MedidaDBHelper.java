package com.example.contacts.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.contacts.model.Medida;

import java.util.LinkedList;
import java.util.List;

public class MedidaDBHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "medida.db"; //RECORDAR
    private static final int DATABASE_VERSION = 3 ;
    public static final String TABLE_NAME = "Medida";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMNA_MEDIDA_FECHA = "fecha";
    public static final String COLUMNA_MEDIDA_GRASA = "grasa";
    public static final String COLUMNA_MEDIDA_MASA = "masa";
    public static final String COLUMNA_MEDIDA_PESO = "peso";
    public static final String COLUMNA_MEDIDA_EDAD = "edad";

    public MedidaDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNA_MEDIDA_FECHA + " TEXT NOT NULL, " +
                COLUMNA_MEDIDA_GRASA   + " INTEGER NOT NULL, " +
                COLUMNA_MEDIDA_MASA + " INTEGER NOT NULL, " +
                COLUMNA_MEDIDA_PESO + " INTEGER NOT NULL, " +
                COLUMNA_MEDIDA_EDAD + " INTEGER NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    /**create record**/
    public void saveNewMedida(Medida medida) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNA_MEDIDA_FECHA , medida.getFecha());
        values.put(COLUMNA_MEDIDA_GRASA, medida.getGrasa());
        values.put(  COLUMNA_MEDIDA_MASA, medida.getmasa());
        values.put( COLUMNA_MEDIDA_PESO, medida.getPeso());
        values.put( COLUMNA_MEDIDA_EDAD, medida.getEdad());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public List<Medida> peopleList(String filter) {
        String query;

        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ COLUMNA_MEDIDA_FECHA +" ASC ";
        }

        List<Medida> personLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Medida medida;

        if (cursor.moveToFirst()) {
            do {
                medida = new Medida();

                medida.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                medida.setFecha(cursor.getString(cursor.getColumnIndex(COLUMNA_MEDIDA_FECHA)));
                medida.setGrasa(cursor.getInt(cursor.getColumnIndex(COLUMNA_MEDIDA_GRASA)));
                medida.setmasa(cursor.getInt(cursor.getColumnIndex(COLUMNA_MEDIDA_MASA)));
                medida.setPeso(cursor.getInt(cursor.getColumnIndex(COLUMNA_MEDIDA_PESO)));
                medida.setEdad(cursor.getInt(cursor.getColumnIndex(COLUMNA_MEDIDA_EDAD)));

                personLinkedList.add(medida);
            } while (cursor.moveToNext());
        }

        return personLinkedList;
    }

    /**Query only 1 record**/
    public Medida getMedida(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id=" + id;
        Cursor cursor = db.rawQuery(query, null);

        Medida receivedPerson = new Medida();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedPerson.setFecha(cursor.getString(cursor.getColumnIndex(COLUMNA_MEDIDA_FECHA)));
            receivedPerson.setGrasa(cursor.getInt(cursor.getColumnIndex(COLUMNA_MEDIDA_GRASA)));
            receivedPerson.setmasa(cursor.getInt(cursor.getColumnIndex(COLUMNA_MEDIDA_MASA)));
            receivedPerson.setPeso(cursor.getInt(cursor.getColumnIndex(COLUMNA_MEDIDA_PESO)));
            receivedPerson.setEdad(cursor.getInt(cursor.getColumnIndex(COLUMNA_MEDIDA_EDAD)));

        }
        return receivedPerson;

    }


    /**delete record**/
    public void deleteMedidaRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE _id='"+id+"'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }

    /**update record**/
    public void updateMedidaRecord(long MedidaId, Context context, Medida updatedMedida) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLE_NAME+" SET fecha ='"+ updatedMedida.getFecha()+ "', grasa ='" + updatedMedida.getGrasa()+ "', masa ='"+ updatedMedida.getmasa() + "', peso ='"+ updatedMedida.getPeso() +"', edad ='"+ updatedMedida.getEdad() + "'  WHERE _id='" + MedidaId + "'");
        Toast.makeText(context, "ACTUALIZACION EXITOZA.", Toast.LENGTH_SHORT).show();


    }


}
