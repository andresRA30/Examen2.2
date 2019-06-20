package com.example.contacts.listedit;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.contacts.data.db.dao.MedidaDao;
import com.example.contacts.data.db.dao.PersonDao;
import com.example.contacts.data.db.entity.Medida;
import com.example.contacts.data.db.entity.Person;

import java.util.List;


public class ListPresenterMedida implements ListContractMedida.Presenter {

    private final ListContractMedida.View mView;
    private final MedidaDao medidaDao;

    public ListPresenterMedida(ListContractMedida.View view, MedidaDao medidaDao) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.medidaDao = medidaDao;
    }

    @Override
    public void start() {

    }

    @Override
    public void addNewMedida() {
        mView.showAddMedida();
    }




    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void populateMedida() {
        medidaDao.findAllMedida().observeForever(new Observer<List<Medida>>() {
            @Override
            public void onChanged(@Nullable List<Medida> medidas) {
                mView.setMedida(medidas);
                if (medidas == null || medidas.size() < 1) {
                    mView.showEmptyMessageMedida();
                }
            }
        });
    }

    @Override
    public void openEditScreenMedida(Medida  medida) {
        mView.showEditScreenMedida(medida.id);
    }

    @Override
    public void openConfirmDeleteDialogMedida(Medida medida) {
        mView.showDeleteConfirmDialogMedida(medida);
    }

    @Override
    public void delete(long medidaId) {
     Medida medida = medidaDao.findMedida(medidaId);
     medidaDao.deleteMedida(medida);
    }
}