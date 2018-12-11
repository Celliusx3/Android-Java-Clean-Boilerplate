package com.app.cellstudio.domain.interactor.impl;

import com.app.cellstudio.domain.entity.Page;
import com.app.cellstudio.domain.interactor.MainInteractor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 11/12/2018.
 */

public class MainInteractorImpl implements MainInteractor {
    public MainInteractorImpl() {
    }

    @Override
    public Observable<List<Page>> getFragmentPages() {
        List<Page> fragmentPages = new ArrayList<>();

        fragmentPages.add(Page.HomePage);
        fragmentPages.add(Page.SettingsPage);

        return Observable.just(fragmentPages);
    }
}
