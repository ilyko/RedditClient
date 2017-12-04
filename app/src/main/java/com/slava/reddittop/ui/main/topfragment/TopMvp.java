package com.slava.reddittop.ui.main.topfragment;

import com.slava.reddittop.model.top.RedditTop;
import com.slava.reddittop.ui.base.Mvp;

public interface TopMvp {
    interface Presenter extends Mvp.Presenter<TopMvp.View>{
        /**
         * Gets data from api with retrofit and rxJava.
         * @param after - last loaded item identifier;
         * @param limit - number of items are returned;
         */
        void getRedditTop(String after, int limit);
    }

    interface View extends Mvp.View{
        /**
         * Pass data to recyclerView;
         * @param redditTop - the object is received from api.
         */
        void showRedditTopPosts(RedditTop redditTop);
    }
}
