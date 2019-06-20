package com.example.contacts.edit;


import com.example.contacts.data.db.dao.MedidaDao;
import com.example.contacts.data.db.dao.PersonDao;
import com.example.contacts.data.db.entity.Medida;
import com.example.contacts.data.db.entity.Person;
import com.example.contacts.utils.Constants;
import com.example.contacts.utils.Util;

public class EditPresenterMedida implements EditContractMedida.Presenter {

    private final EditContractMedida.View mView;
    private final MedidaDao medidaDao;

    public EditPresenterMedida(EditContractMedida.View mMainView, MedidaDao medidaDao) {
        this.mView = mMainView;
        this.mView.setPresenter(this);
        this.medidaDao = medidaDao;
    }

    @Override
    public void start() {

    }

    @Override
    public void save(Medida medida) {
        long ids = this.medidaDao.insertMedida(medida);
        mView.close();
    }

    @Override
    public boolean validate(Medida medida) {
        mView.clearPreErrors();
        if (medida.fecha==null) {
            mView.showErrorMessage(Constants.FIELD_FECHA);
            return false;
        }
        if (String.valueOf(medida.grasa).isEmpty()) {
            mView.showErrorMessage(Constants.FIELD_GRASA);
            return false;
        }
        if (String.valueOf(medida.peso).isEmpty()) {
            mView.showErrorMessage(Constants.FIELD_PESO);
            return false;
        }
        if (String.valueOf(medida.masa).isEmpty()) {
            mView.showErrorMessage(Constants.FIELD_MASA);
            return false;
        }
        if (String.valueOf(medida.edad).isEmpty()) {
            mView.showErrorMessage(Constants.FIELD_EDAD);
            return false;
        }

        return true;
    }

    @Override
    public void showDateDialog() {

        mView.openDateDialog();
    }

    @Override
    public void getMedidaAndPopulate(long id) {
        Medida medida = medidaDao.findMedida(id);
        if (medida != null) {
            mView.populate(medida);
        }
    }

    @Override
    public void update(Medida medida) {
        int ids = this.medidaDao.updateMedida(medida);
        mView.close();
    }
}
