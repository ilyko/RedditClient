package com.slava.reddittop.ui.base;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * base implementation of pagination for recyclerview;
 */
public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;
    private int visibleThreshold = 1;

    protected PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        if (!isLoading() && !isLastPage()) {
            if ((lastVisibleItemPosition + visibleThreshold) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }
    }


    protected abstract void loadMoreItems();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
