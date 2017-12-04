package com.slava.reddittop.ui.main.topfragment;

import com.slava.reddittop.model.top.RedditTop;
import com.slava.reddittop.ui.base.Mvp;

public interface TopMvp {
    interface Presenter extends Mvp.Presenter<TopMvp.View>{
        void getRedditTop(String after, int limit);
    }

    interface View extends Mvp.View{
        void showRedditTopPosts(RedditTop redditTop);
    }
}
