package com.app.cellstudio.presentation.presentation.view.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsFragment extends BaseFragment {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @Inject
    BaseSchedulerProvider scheduler;

    @BindView(R.id.rl_settings)
    RelativeLayout rlSettings;

    public static SettingsFragment newInstance() {
        final Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
    }

    @Override
    protected void onInject() {
    }

    @Override
    protected void onBindData(View view) {
        super.onBindData(view);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @OnClick(R.id.rl_settings_credits)
    public void onCreditsClicked() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setMessage("Thank you for supporting us.")
                .setPositiveButton("OK", (dialog, which) -> {
                    if (null != dialog) {
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }
}