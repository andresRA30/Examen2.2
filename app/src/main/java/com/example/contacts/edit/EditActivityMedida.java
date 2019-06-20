package com.example.contacts.edit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.contacts.R;
import com.example.contacts.data.db.AppDatabase;
import com.example.contacts.data.db.entity.Medida;
import com.example.contacts.data.db.entity.Person;
import com.example.contacts.utils.Constants;
import com.example.contacts.utils.Util;

import java.util.Date;


public class EditActivityMedida extends AppCompatActivity implements EditContractMedida.View, EditContractMedida.DateListener {

    private EditContractMedida.Presenter mPresenter;

    private EditText mFechaEditText;
    private EditText mGrasaEditText;
    private EditText mMasaEditText;
    private EditText mPesoEditText;
    private EditText mEdadEditText;

    private TextInputLayout fechaNTextInputLayout;
    private TextInputLayout grasaNTextInputLayout;
    private TextInputLayout masaNTextInputLayout;
    private TextInputLayout pesoNTextInputLayout;
    private TextInputLayout edadNTextInputLayout;

    private FloatingActionButton mFab;

    private Medida medida;
    private boolean mEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_medida);

        medida= new Medida();
        checkMode();

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        mPresenter = new EditPresenterMedida(this, db.medidaModel());

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mEditMode) {
            mPresenter.getMedidaAndPopulate(medida.id);
        }
    }

    private void checkMode() {
        if (getIntent().getExtras() != null) {
            medida.id = getIntent().getLongExtra(Constants.MEDIDA_ID, 0);
            mEditMode = true;
        }
    }

    private void initViews() {
        mFechaEditText = (EditText) findViewById(R.id.fechaN);
        mGrasaEditText = (EditText) findViewById(R.id.grasaN);
        mMasaEditText = (EditText) findViewById(R.id.masaN);
        mPesoEditText = (EditText) findViewById(R.id.pesoN);
        mEdadEditText = (EditText) findViewById(R.id.edadN);

        fechaNTextInputLayout = (TextInputLayout) findViewById(R.id.fechaNTextInputLayout);
        grasaNTextInputLayout= (TextInputLayout) findViewById(R.id.grasaNTextInputLayout);
        masaNTextInputLayout = (TextInputLayout) findViewById(R.id.masaNTextInputLayout);
        pesoNTextInputLayout = (TextInputLayout) findViewById(R.id.pesoNTextInputLayout);
        edadNTextInputLayout = (TextInputLayout) findViewById(R.id.edadNTextInputLayout);

        mFechaEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showDateDialog();
            }
        });

        mFab = (FloatingActionButton) findViewById(R.id.fabMedida);
        mFab.setImageResource(mEditMode ? R.drawable.ic_refresh : R.drawable.ic_done);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                medida.masa = Integer.parseInt(mMasaEditText.getText().toString());
                medida.grasa = Integer.parseInt(mGrasaEditText.getText().toString());
                medida.peso = Integer.parseInt(mPesoEditText.getText().toString());
                medida.edad = Integer.parseInt(mEdadEditText.getText().toString());

                boolean valid = mPresenter.validate(medida);

                if (!valid) return;

                if (mEditMode) {
                    mPresenter.update(medida);
                } else {
                    mPresenter.save(medida);
                }
            }
        });
    }

    @Override
    public void setPresenter(EditContractMedida.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showErrorMessage(int field) {
        if (field == Constants.FIELD_FECHA) {
            fechaNTextInputLayout.setError(getString(R.string.invalid_fecha));
        } else if (field == Constants.FIELD_GRASA) {
            grasaNTextInputLayout.setError(getString(R.string.invalid_grasa));
        } else if (field == Constants.FIELD_MASA) {
            masaNTextInputLayout.setError(getString(R.string.invalid_masa));
        } else if (field == Constants.FIELD_PESO) {
            pesoNTextInputLayout.setError(getString(R.string.invalid_peso));
        } else if (field == Constants.FIELD_EDAD) {
            edadNTextInputLayout.setError(getString(R.string.invalid_edad));
        }
    }

    @Override
    public void clearPreErrors() {
        fechaNTextInputLayout.setErrorEnabled(false);
        grasaNTextInputLayout.setErrorEnabled(false);
        masaNTextInputLayout.setErrorEnabled(false);
        pesoNTextInputLayout.setErrorEnabled(false);
        edadNTextInputLayout.setErrorEnabled(false);
    }

    @Override
    public void openDateDialog() {
        DateDialogFragment fragment = new DateDialogFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void populate(Medida medida) {
        this.medida = medida;
        mFechaEditText.setText(Util.format(medida.fecha));
        mGrasaEditText.setText(medida.grasa);
        mMasaEditText.setText(medida.masa);
        mPesoEditText.setText(medida.peso);
        mEdadEditText.setText(medida.edad);
    }

    @Override
    public void setSelectedDateMedida(Date date) {
        medida.fecha = date;
        mFechaEditText.setText(Util.format(date));
    }
}