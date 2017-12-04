package com.slava.reddittop.ui.base;


public interface Mvp {
    interface Presenter<V extends View> {

        void attachView(V view);

        void detachView();

    }

    interface View {
        /**
         * show error message
         */
        void handleError();
    }
}
