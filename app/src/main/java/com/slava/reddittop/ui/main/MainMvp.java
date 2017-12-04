package com.slava.reddittop.ui.main;

import com.slava.reddittop.ui.base.Mvp;

public interface MainMvp {

    interface View extends Mvp.View {

    }

    interface Presenter extends Mvp.Presenter<View> {

    }
}
