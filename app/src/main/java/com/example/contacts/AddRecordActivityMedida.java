package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contacts.model.Medida;
import com.example.contacts.utils.MedidaDBHelper;

public class AddRecordActivityMedida extends AppCompatActivity {

    private EditText mFechaEditText;
    private EditText mGrasaEditText;
    private EditText mMasaEditText;
    private EditText mPesoEditText;
    private EditText mEdadEditText;
    private Button mAddBtn;

    private MedidaDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_medida);
        //REVISAR LO DEJE DIFERENTE LE ANADI EL CASTEO
        mFechaEditText = (EditText) findViewById(R.id.fechaN);
        mGrasaEditText = (EditText) findViewById(R.id.grasaN);
        mMasaEditText = (EditText) findViewById(R.id.masaN);
        mPesoEditText = (EditText) findViewById(R.id.pesoN);
        mEdadEditText = (EditText) findViewById(R.id.edadN);

        mAddBtn = (Button) findViewById(R.id.addNewUserButton);



        //listen to add button click
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                saveMedida();
            }
        });

    }

    private void saveMedida(){
        String fecha=  mFechaEditText.getText().toString().trim();
        int grasa =  Integer.parseInt(mGrasaEditText.getText().toString().trim());
        int masa = Integer.parseInt( mMasaEditText.getText().toString().trim());
        int peso =   Integer.parseInt(mPesoEditText.getText().toString().trim());
        int edad = Integer.parseInt(mEdadEditText.getText().toString().trim()) ;

        dbHelper = new MedidaDBHelper(this);

        if(fecha.isEmpty()){
            //error name is empty
            Toast.makeText(this, "Debes ingresar una fecha ", Toast.LENGTH_SHORT).show();
        }

        if(String.valueOf(grasa).isEmpty() ){
            //error name is empty
            Toast.makeText(this, "Debes ingresar la grasa ", Toast.LENGTH_SHORT).show();
        }

        if( String.valueOf(masa).isEmpty()){
            //error name is empty
            Toast.makeText(this, "Debes ingresar la  masa muscular ", Toast.LENGTH_SHORT).show();
        }

        if( String.valueOf(peso).isEmpty() ){
            //error name is empty
            Toast.makeText(this, "Debes ingresar el peso ", Toast.LENGTH_SHORT).show();
        }

        if( String.valueOf(edad).isEmpty()){
            //error name is empty
            Toast.makeText(this, "Debes ingresar la  edad", Toast.LENGTH_SHORT).show();
        }


        //create new person
        Medida medida = new Medida(fecha, grasa, masa, peso, edad);
        dbHelper.saveNewMedida(medida);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(AddRecordActivityMedida.this, activity_historial.class));
    }















}
