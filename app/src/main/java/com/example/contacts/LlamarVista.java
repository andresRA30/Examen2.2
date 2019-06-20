package com.example.contacts;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LlamarVista extends AppCompatActivity {

    private Context mContext;

    public LlamarVista() {

    }



    public void Siguiente(){

      /*  Intent llamarVista = new Intent(mContext,activity_historial.class);
 mContext.startActivity(llamarVista); // resive por parametro el objeto de la nueva actividad*/
        Bundle parametros = this.getIntent().getExtras();
        onCreate(parametros);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);



    }







}
