package com.app.cellstudio.presentation.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.cellstudio.domain.entity.Page;
import com.app.cellstudio.presentation.BaseApplication;
import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.di.modules.MainModule;
import com.app.cellstudio.presentation.interactor.viewmodel.MainViewModel;
import com.app.cellstudio.presentation.presentation.view.adapter.MainPagerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainViewModel mainViewModel;

    @BindView(R.id.rl_main)
    RelativeLayout rlMain;

    @BindView(R.id.main_vp_content)
    ViewPager vpContent;

    @BindView(R.id.bnv_main)
    BottomNavigationView bnvMain;

    private MainPagerAdapter mainPagerAdapter;
    private List<Page> fragmentPages;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected View getRootView() {
        return rlMain;
    }

    protected void onInject() {
        BaseApplication.getInstance()
                .getApplicationComponent()
                .plus(new MainModule(this))
                .inject(this);
    }

    @Override
    protected void onBindView() {
        super.onBindView();
    }

    @Override
    protected void onBindData(View view, Bundle savedInstanceState) {
        super.onBindData(view, savedInstanceState);

        mainViewModel.getFragmentPages()
                .compose(bindToLifecycle())
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler())
                .subscribe(pages -> {
                    this.fragmentPages = pages;
                    setupBottomNavigationView(fragmentPages);
                    setupMainPagerAdapter(fragmentPages);
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupBottomNavigationView(List<Page> pages) {
        if (bnvMain == null)
            return;

        Menu menu = bnvMain.getMenu();

        for (Page page:pages) {
            if (page.equals(Page.HomePage)) {
                menu.add(Menu.NONE, page.getPageId(), Menu.NONE,
                        page.getTitle()).setIcon(R.drawable.ic_home_white_24dp);
            } else if (page.equals(Page.SettingsPage)) {
                menu.add(Menu.NONE, page.getPageId(), Menu.NONE,
                        page.getTitle()).setIcon(R.drawable.ic_settings_white_24dp);
            } else {
                menu.add(Menu.NONE, page.getPageId(), Menu.NONE,
                        page.getTitle()).setIcon(R.drawable.ic_broken_image_24dp);
            }
        }

        bnvMain.setOnNavigationItemSelectedListener(item -> {
            this.setPage(item.getItemId());
            return true;
        });
    }

    private void setPage (int pageId) {
        vpContent.setCurrentItem(mainPagerAdapter.getPagePositionById(pageId));
    }

    private void setupMainPagerAdapter(List<Page> pages) {
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), pages);
        vpContent.setAdapter(mainPagerAdapter);
    }
}
