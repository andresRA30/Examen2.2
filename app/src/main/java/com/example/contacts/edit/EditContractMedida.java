package com.example.contacts.edit;


import com.example.contacts.BasePresenter;
import com.example.contacts.BaseView;
import com.example.contacts.data.db.entity.Medida;
import com.example.contacts.data.db.entity.Person;

import java.util.Date;


public interface EditContractMedida {

    interface Presenter extends BasePresenter {
        void save(Medida medida);

        boolean validate(Medida medida);

        void showDateDialog();

        void getMedidaAndPopulate(long id);

        void update(Medida  medida);
    }

    interface View extends BaseView<Presenter> {

        void showErrorMessage(int field);

        void clearPreErrors();

        void openDateDialog();

        void close();

        void populate(Medida medida);
    }

    interface DateListener {

        void setSelectedDateMedida(Date date);

    }
}