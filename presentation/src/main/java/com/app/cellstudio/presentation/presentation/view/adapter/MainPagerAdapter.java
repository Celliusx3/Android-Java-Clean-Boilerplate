package com.app.cellstudio.presentation.presentation.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.cellstudio.domain.entity.Page;
import com.app.cellstudio.presentation.presentation.view.fragment.HomeFragment;
import com.app.cellstudio.presentation.presentation.view.fragment.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coyan on 11/12/2018.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Page> fragmentPages;
    private final List<Integer> fragmentPageIds = new ArrayList<>();


    private HomeFragment homeFragment;
    private SettingsFragment settingsFragment;

    public MainPagerAdapter(final FragmentManager fragmentManager, final List<Page> fragmentPages) {
        super(fragmentManager);

        this.fragmentPages = fragmentPages;

        fragmentPageIds.clear();
        for (Page page : fragmentPages) {
            fragmentPageIds.add(page.getPageId());
        }
    }

    @Override
    public Fragment getItem(int position) {
        Page fragmentPage = fragmentPages.get(position);

        if (fragmentPage.equals(Page.HomePage)) {
            if (homeFragment == null) {
                homeFragment = HomeFragment.newInstance();
            }
            return homeFragment;
        } else if (fragmentPage.equals(Page.SettingsPage)) {
            if (settingsFragment == null) {
                settingsFragment = SettingsFragment.newInstance();
            }
            return settingsFragment;
        } else {
            if (homeFragment == null) {
                homeFragment = HomeFragment.newInstance();
            }
            return homeFragment;
        }
    }

    @Override
    public int getCount() {
        return fragmentPages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentPages.get(position).getTitle();
    }

    public int getPagePositionById(int pageId) {
        return fragmentPageIds.indexOf(pageId);
    }
}
