package com.example.contacts.utils;

import android.support.v7.widget.RecyclerView;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;// para llamar la activity historial
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contacts.UpdateRecordActivityMedida;
import com.example.contacts.model.Medida;

import com.example.contacts.R;



import java.util.List;

public class MedidaAdapter extends RecyclerView.Adapter<MedidaAdapter.ViewHolder> {
    private List<Medida> mMedidaList;
    private Context mContext;
    private RecyclerView mRecyclerV;



    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView fechaTxtV;
        public TextView grasaTxtV;
        public TextView masaTxtV;
        public TextView pesoTxtV;
        public TextView edadTxtV;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            fechaTxtV =  v.findViewById(R.id.fecha);
            grasaTxtV =  v.findViewById(R.id.grasa);
            masaTxtV =  v.findViewById(R.id.masa);
            pesoTxtV = v.findViewById(R.id.peso);
            edadTxtV =  v.findViewById(R.id.edad);

        }
    }


    public void add(int position, Medida medida) {
        mMedidaList.add(position, medida);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mMedidaList.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MedidaAdapter(List<Medida> myDataset, Context context, RecyclerView recyclerView) {
        mMedidaList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MedidaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.lista_historial, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MedidaAdapter.ViewHolder vh = new MedidaAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MedidaAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Medida medida = mMedidaList.get(position);
        holder.fechaTxtV.setText("Fecha: " + medida.getFecha());
        holder.grasaTxtV.setText("Grasa: " + medida.getGrasa());
        holder.masaTxtV.setText("masa: " + medida.getmasa());
        holder.pesoTxtV.setText("Peso: " + medida.getPeso());
        holder.edadTxtV.setText("Edad: " + medida.getEdad());


        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Selecione una opcion");
                builder.setMessage("Actualiaze o elimine esta medida en esta fecha?");
                builder.setPositiveButton("actualice", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        //goToUpdateActivity(estudiante.getId());

                    }
                });
                builder.setNeutralButton("Elimine", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MedidaDBHelper dbHelper = new MedidaDBHelper(mContext);
                        dbHelper.deleteMedidaRecord(medida.getId(), mContext);

                        mMedidaList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mMedidaList.size());
                        notifyDataSetChanged();
                    }
                });

                 builder.setNegativeButton("Cancele", new DialogInterface.OnClickListener() {
                     @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                 });

                builder.create().show();
            }
        });


    }




    private void goToUpdateActivity(long personId){
        Intent goToUpdate = new Intent(mContext, UpdateRecordActivityMedida.class);
        goToUpdate.putExtra("USER_ID", personId);
        mContext.startActivity(goToUpdate);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mMedidaList.size();
    }
















}
