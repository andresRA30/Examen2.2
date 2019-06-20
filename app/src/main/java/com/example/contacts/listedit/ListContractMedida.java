package com.example.contacts.listedit;


import com.example.contacts.BasePresenter;
import com.example.contacts.BaseView;
import com.example.contacts.data.db.entity.Medida;
import com.example.contacts.data.db.entity.Person;

import java.util.List;


public interface ListContractMedida {

    interface Presenter extends BasePresenter {

        void addNewMedida();


        void result(int requestCode, int resultCode);

        void populateMedida();

        void openEditScreenMedida(Medida medida);

        void openConfirmDeleteDialogMedida(Medida medida);

        void delete(long medidaId);
    }

    interface View extends BaseView<Presenter> {




        void showAddMedida();

        void setMedida(List<Medida> medida);

        void showEditScreenMedida(long id);

        void showDeleteConfirmDialogMedida(Medida medida);

        void showEmptyMessageMedida();
    }

    interface OnItemClickListener {

        void clickItem(Medida medida);

        void clickLongItem(Medida medida);
    }

    interface DeleteListener {

        void setConfirm(boolean confirm, long medidaId);

    }
}