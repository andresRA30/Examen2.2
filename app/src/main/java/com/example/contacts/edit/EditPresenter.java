package com.example.contacts.edit;


import com.example.contacts.data.db.dao.PersonDao;
import com.example.contacts.data.db.entity.Person;
import com.example.contacts.utils.Constants;
import com.example.contacts.utils.Util;

public class EditPresenter implements EditContract.Presenter {

    private final EditContract.View mView;
    private final PersonDao personDao;

    public EditPresenter(EditContract.View mMainView, PersonDao personDao) {
        this.mView = mMainView;
        this.mView.setPresenter(this);
        this.personDao = personDao;
    }

    @Override
    public void start() {

    }

    @Override
    public void save(Person person) {
        long ids = this.personDao.insertPerson(person);
        mView.close();
    }

    @Override
    public boolean validate(Person person) {
        mView.clearPreErrors();
        if (person.name.isEmpty() || !Util.isValidName(person.name)) {
            mView.showErrorMessage(Constants.FIELD_NAME);
            return false;
        }
        if (person.edad.isEmpty()) {
            mView.showErrorMessage(Constants.FIELD_edad);
            return false;
        }
        if (person.foto.isEmpty() || !Util.isValidfoto(person.foto)) {
            mView.showErrorMessage(Constants.FIELD_foto);
            return false;
        }
        if (person.email.isEmpty() || !Util.isValidEmail(person.email)) {
            mView.showErrorMessage(Constants.FIELD_EMAIL);
            return false;
        }


        return true;
    }



    @Override
    public void getPersonAndPopulate(long id) {
        Person person = personDao.findPerson(id);
        if (person != null) {
            mView.populate(person);
        }
    }

    @Override
    public void update(Person person) {
        int ids = this.personDao.updatePerson(person);
        mView.close();
    }
}
