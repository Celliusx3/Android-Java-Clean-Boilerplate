package com.app.cellstudio.domain.interactor;

import com.app.cellstudio.domain.entity.Page;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 11/12/2018.
 */

public interface MainInteractor {
    Observable<List<Page>> getFragmentPages();
}
