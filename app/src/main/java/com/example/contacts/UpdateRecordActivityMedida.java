package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contacts.model.Medida;
import com.example.contacts.utils.MedidaDBHelper;

public class UpdateRecordActivityMedida extends AppCompatActivity {

    private EditText mFechaEditText;
    private EditText mGrasaEditText;
    private EditText mMasaEditText;
    private EditText mPesoEditText;
    private EditText mEdadEditText;
    private Button mUpdateBtn;

    private MedidaDBHelper dbHelper;
    private long receivedPersonId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record_medida);

        //REVISAR ACA TAMBIEN ANADI EL CASTEO
        mFechaEditText = (EditText) findViewById(R.id.userFechaUpdate);
        mGrasaEditText = (EditText) findViewById(R.id.userGrasaUpdate);
        mMasaEditText = (EditText) findViewById(R.id.userMasaUpdate);
        mPesoEditText = (EditText) findViewById(R.id.userPesoUpdate);
        mEdadEditText = (EditText) findViewById(R.id.userEdadUpdate);


        mUpdateBtn = (Button)findViewById(R.id.updateUserButton);

        dbHelper = new MedidaDBHelper(this);

        try {
            //get intent to get person id
            receivedPersonId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***populate user data before update***/
        Medida queriedPerson = dbHelper.getMedida(receivedPersonId);
        //set field to this user data
        mFechaEditText.setText(queriedPerson.getFecha());
        mGrasaEditText.setText(queriedPerson.getGrasa());
        mMasaEditText.setText(queriedPerson.getmasa());
        mPesoEditText.setText(queriedPerson.getPeso());
        mEdadEditText.setText(queriedPerson.getEdad());



        //listen to add button click to update
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                updatePerson();
            }
        });


    }



    private void updatePerson(){

        String fecha = mFechaEditText.getText().toString().trim();
        int grasa =  Integer.parseInt(mGrasaEditText.getText().toString().trim());
        int masa = Integer.parseInt( mMasaEditText.getText().toString().trim());
        int peso = Integer.parseInt( mPesoEditText.getText().toString().trim());
        int edad =  Integer.parseInt( mEdadEditText.getText().toString().trim());

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

        //create updated person
        Medida updatedPerson = new Medida(fecha, grasa, masa, peso, edad );

        //call dbhelper update
        dbHelper.updateMedidaRecord(receivedPersonId, this, updatedPerson);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }


    private void goBackHome(){
        startActivity(new Intent(this, activity_historial.class)); // REVISAR
    }



























}
