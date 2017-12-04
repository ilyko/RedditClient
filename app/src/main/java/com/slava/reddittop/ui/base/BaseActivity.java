package com.slava.reddittop.ui.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements Mvp.View {
    private Unbinder mUnBinder;

    /**
     * @param unBinder - unbinds butterknife's dependencies;
     */
    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    /**
     * helper for adding fragment to activity;
     *
     * @param containerViewId - container of activity;
     * @param fragment        - class of added fragment;
     */
    protected final void addFragment(@IdRes int containerViewId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(containerViewId, fragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void handleError() {

    }

}
