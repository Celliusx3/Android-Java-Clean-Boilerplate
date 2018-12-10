package com.app.cellstudio.presentation.di.components;

import com.app.cellstudio.presentation.di.modules.MovieDetailsModule;
import com.app.cellstudio.presentation.presentation.view.activity.MovieDetailsActivity;

import dagger.Subcomponent;

/**
 * Created by coyan on 04/12/2018.
 */

@ActivityScope
@Subcomponent(modules = {MovieDetailsModule.class})
public interface MovieDetailsComponent {
    void inject(MovieDetailsActivity movieDetailsActivity);
}
