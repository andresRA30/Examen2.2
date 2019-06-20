package com.example.contacts.edit;


import java.util.Date;

import com.example.contacts.BasePresenter;
import com.example.contacts.BaseView;
import com.example.contacts.data.db.entity.Person;


public interface EditContract {

    interface Presenter extends BasePresenter {
        void save(Person person);

        boolean validate(Person person);



        void getPersonAndPopulate(long id);

        void update(Person person);
    }

    interface View extends BaseView<Presenter> {

        void showErrorMessage(int field);

        void clearPreErrors();

        void openDateDialog();

        void close();

        void populate(Person person);
    }


}