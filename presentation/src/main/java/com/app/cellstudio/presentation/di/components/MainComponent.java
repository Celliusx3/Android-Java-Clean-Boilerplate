package com.app.cellstudio.presentation.di.components;

import com.app.cellstudio.presentation.di.modules.MainModule;
import com.app.cellstudio.presentation.presentation.view.activity.MainActivity;

import dagger.Subcomponent;

/**
 * Created by coyan on 29/11/2018.
 */

@ActivityScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
