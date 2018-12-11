package com.app.cellstudio.presentation.interactor.viewmodel;

import com.app.cellstudio.domain.entity.Page;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 30/11/2018.
 */

public interface MainViewModel extends ViewModel {
    Observable<List<Page>> getFragmentPages();
}
