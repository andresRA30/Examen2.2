package com.example.contacts.listedit;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.contacts.LlamarVista;
import com.example.contacts.R;
import com.example.contacts.utils.Constants;

public class DeleteConfirmFragment extends DialogFragment {

    private ListContract.DeleteListener mListener;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final long personId = getArguments().getLong(Constants.PERSON_ID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.are_you_sure);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.setConfirm(true, personId);
            }
        });


        builder.setNegativeButton("VISTA HISTORIAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // mListener.setConfirm(false, personId);
                LlamarVista l = new LlamarVista();
                l.Siguiente();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ListContract.DeleteListener) context;
    }
}