package com.example.contacts.listedit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.contacts.R;
import com.example.contacts.data.db.entity.Medida;
import com.example.contacts.utils.Util;

import java.util.ArrayList;
import java.util.List;


public class MedidaAdapter extends RecyclerView.Adapter<MedidaAdapter.ViewHolder> {

    private List<Medida> mValues;
    private ListContractMedida.OnItemClickListener mOnItemClickListener;

    public MedidaAdapter(ListContractMedida.OnItemClickListener onItemClickListener) {
        mValues = new ArrayList<>();
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_historial, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.fechaTextView.setText(Util.formatMin(mValues.get(position).fecha));
        holder.grasaTextView.setText(mValues.get(position).grasa);
        holder.pesoTextView.setText(mValues.get(position).peso);
        holder.masaTextView.setText(mValues.get(position).masa);
        holder.edadTextView.setText(mValues.get(position).edad);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.clickItem(holder.mItem);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemClickListener.clickLongItem(holder.mItem);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setValues(List<Medida> values) {
        mValues = values;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView fechaTextView;
        public final TextView grasaTextView;
        public final TextView pesoTextView;
        public final TextView masaTextView;
        public final TextView edadTextView;
        public Medida mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            fechaTextView = (TextView) view.findViewById(R.id.fecha);
            grasaTextView = (TextView) view.findViewById(R.id.grasa);
            pesoTextView = (TextView) view.findViewById(R.id.peso);
            masaTextView = (TextView) view.findViewById(R.id.masa);
            edadTextView = (TextView) view.findViewById(R.id.edad);
        }
    }


}