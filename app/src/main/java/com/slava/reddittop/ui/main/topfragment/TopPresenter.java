package com.slava.reddittop.ui.main.topfragment;

import com.slava.reddittop.api.RestApi;
import com.slava.reddittop.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TopPresenter extends BasePresenter<TopMvp.View> implements TopMvp.Presenter {

    @Override
    public void getRedditTop(String after, int limit) {
        compositeDisposable.add(RestApi
                .factory()
                .getRedditTop(after, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        redditTop -> view.showRedditTopPosts(redditTop),
                        throwable -> view.handleError()));
    }
}
