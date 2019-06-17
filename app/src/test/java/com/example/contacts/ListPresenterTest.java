package com.example.contacts;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.contacts.edit.EditContract;
import com.example.contacts.edit.EditPresenter;
import com.example.contacts.listedit.ListContract;
import com.example.contacts.listedit.ListPresenter;

import static org.mockito.Mockito.verify;


public class ListPresenterTest {
    @Mock
    private ListContract.View mListView;

    @Mock
    private EditContract.View mEditView;

    private ListPresenter mListPresenter;

    private EditPresenter mEditPresenter;

    @Before
    public void setupMocksAndView() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPresenter_setsThePresenterToView_list() {
        mListPresenter = new ListPresenter(mListView, null);

        verify(mListView).setPresenter(mListPresenter);
    }

    @Test
    public void createPresenter_setsThePresenterToView_edit() {
        mEditPresenter = new EditPresenter(mEditView, null);

        verify(mEditView).setPresenter(mEditPresenter);
    }
}