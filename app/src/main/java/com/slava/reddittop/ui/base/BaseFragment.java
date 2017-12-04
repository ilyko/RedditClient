package com.slava.reddittop.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment implements Mvp.View {
    private Unbinder unbinder;

    public BaseFragment() {
        setArguments(new Bundle());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public abstract int getLayout();

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();

    }
}