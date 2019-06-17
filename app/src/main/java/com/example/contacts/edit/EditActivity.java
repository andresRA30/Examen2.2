package com.example.contacts.edit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import com.example.contacts.R;
import com.example.contacts.data.db.AppDatabase;
import com.example.contacts.data.db.entity.Person;
import com.example.contacts.utils.Constants;
import com.example.contacts.utils.Util;



public class EditActivity extends AppCompatActivity implements EditContract.View, EditContract.DateListener {

    private EditContract.Presenter mPresenter;

    private EditText mNameEditText;
    private EditText mEdadEditText;
    private EditText mEmailEditText;
    private EditText mBirthdayEditText;
    private EditText mFotoEditText;

    private TextInputLayout mNameTextInputLayout;
    private TextInputLayout mEdadInputLayout;
    private TextInputLayout mEmailInputLayout;
    private TextInputLayout mBirthdayInputLayout;
    private TextInputLayout mFotoTextInputLayout;

    private FloatingActionButton mFab;

    private Person person;
    private boolean mEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        person = new Person();
        checkMode();

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        mPresenter = new EditPresenter(this, db.personModel());

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mEditMode) {
            mPresenter.getPersonAndPopulate(person.id);
        }
    }

    private void checkMode() {
        if (getIntent().getExtras() != null) {
            person.id = getIntent().getLongExtra(Constants.PERSON_ID, 0);
            mEditMode = true;
        }
    }

    private void initViews() {
        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mEdadEditText = (EditText) findViewById(R.id.edadEditText);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mBirthdayEditText = (EditText) findViewById(R.id.birthdayEditText);
        mFotoEditText = (EditText) findViewById(R.id.fotoEditText);

        mNameTextInputLayout = (TextInputLayout) findViewById(R.id.nameTextInputLayout);
        mEdadInputLayout = (TextInputLayout) findViewById(R.id.edadTextInputLayout);
        mEmailInputLayout = (TextInputLayout) findViewById(R.id.emailTextInputLayout);
        mBirthdayInputLayout = (TextInputLayout) findViewById(R.id.birthdayTextInputLayout);
        mFotoTextInputLayout = (TextInputLayout) findViewById(R.id.fotoTextInputLayout);

        mBirthdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showDateDialog();
            }
        });

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setImageResource(mEditMode ? R.drawable.ic_refresh : R.drawable.ic_done);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                person.name = mNameEditText.getText().toString();
                person.edad = mEdadEditText.getText().toString();
                person.email = mEmailEditText.getText().toString();
                person.foto = mFotoEditText.getText().toString();

                boolean valid = mPresenter.validate(person);

                if (!valid) return;

                if (mEditMode) {
                    mPresenter.update(person);
                } else {
                    mPresenter.save(person);
                }
            }
        });
    }

    @Override
    public void setPresenter(EditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showErrorMessage(int field) {
        if (field == Constants.FIELD_NAME) {
            mNameTextInputLayout.setError(getString(R.string.invalid_name));
        } else if (field == Constants.FIELD_EMAIL) {
            mEmailInputLayout.setError(getString(R.string.invalid_email));
        } else if (field == Constants.FIELD_foto) {
            mFotoTextInputLayout.setError(getString(R.string.invalid_foto));
        } else if (field == Constants.FIELD_edad) {
            mEdadInputLayout.setError(getString(R.string.invalid_edad));
        } else if (field == Constants.FIELD_BIRTHDAY) {
            mBirthdayInputLayout.setError(getString(R.string.invalid_birthday));
        }
    }

    @Override
    public void clearPreErrors() {
        mNameTextInputLayout.setErrorEnabled(false);
        mEmailInputLayout.setErrorEnabled(false);
        mFotoTextInputLayout.setErrorEnabled(false);
        mEdadInputLayout.setErrorEnabled(false);
        mBirthdayInputLayout.setErrorEnabled(false);
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
    public void populate(Person person) {
        this.person = person;
        mNameEditText.setText(person.name);
        mEdadEditText.setText(person.edad);
        mEmailEditText.setText(person.email);
        mBirthdayEditText.setText(Util.format(person.birthday));
        mFotoEditText.setText(person.foto);
    }

    @Override
    public void setSelectedDate(Date date) {
        person.birthday = date;
        mBirthdayEditText.setText(Util.format(date));
    }
}