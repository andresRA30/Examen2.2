package com.example.contacts.listedit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.contacts.R;
import com.example.contacts.data.db.AppDatabase;
import com.example.contacts.data.db.entity.Medida;
import com.example.contacts.data.db.entity.Person;
import com.example.contacts.edit.EditActivity;
import com.example.contacts.edit.EditActivityMedida;
import com.example.contacts.utils.Constants;

import java.util.List;


public class ListActivityMedida extends AppCompatActivity implements ListContractMedida.View, ListContractMedida.OnItemClickListener, ListContractMedida.DeleteListener {

    private ListContractMedida.Presenter mPresenter;
    private MedidaAdapter mMedidaAdapter;

    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);



       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabMedida);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewMedida();
            }
        });

        mEmptyTextView = (TextView) findViewById(R.id.emptyTextView2);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listMedidas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMedidaAdapter = new MedidaAdapter(this);
        recyclerView.setAdapter(mMedidaAdapter);

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        mPresenter = new ListPresenterMedida(this,db.medidaModel());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.populateMedida();
    }



    @Override
    public void showAddMedida() {
        Intent intent = new Intent(this, EditActivityMedida.class);
        startActivity(intent);
    }

    @Override
    public void setMedida(List<Medida> medida) {
        mEmptyTextView.setVisibility(View.GONE);
        mMedidaAdapter.setValues(medida);
    }

    @Override
    public void showEditScreenMedida(long id) {
        Intent intent = new Intent(this, EditActivityMedida.class);
        intent.putExtra(Constants.MEDIDA_ID, id);
        startActivity(intent);
    }

    @Override
    public void showDeleteConfirmDialogMedida(Medida medida) {
        DeleteConfirmFragment fragment = new DeleteConfirmFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.MEDIDA_ID, medida.id);
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "confirmDialog");
    }

    @Override
    public void showEmptyMessageMedida() {
        mEmptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(ListContractMedida.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void clickItem(Medida medida) {
        mPresenter.openEditScreenMedida(medida);

    }

    @Override
    public void clickLongItem(Medida medida) {
        mPresenter.openConfirmDeleteDialogMedida(medida);
    }

    @Override
    public void setConfirm(boolean confirm, long medidaId) {
        if (confirm) {
            mPresenter.delete(medidaId);
        }
    }
}
