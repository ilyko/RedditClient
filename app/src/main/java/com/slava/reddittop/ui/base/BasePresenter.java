package com.slava.reddittop.ui.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends Mvp.View> implements Mvp.Presenter<V> {
    protected V view;
    protected CompositeDisposable compositeDisposable;

    @Override
    public void attachView(V view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }
}
