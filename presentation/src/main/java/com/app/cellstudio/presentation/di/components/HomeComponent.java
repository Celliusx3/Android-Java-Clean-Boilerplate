package com.app.cellstudio.presentation.di.components;

import com.app.cellstudio.presentation.di.modules.HomeModule;
import com.app.cellstudio.presentation.presentation.view.fragment.HomeFragment;

import dagger.Subcomponent;

/**
 * Created by coyan on 11/12/2018.
 */

@ActivityScope
@Subcomponent(modules = {HomeModule.class})
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}
