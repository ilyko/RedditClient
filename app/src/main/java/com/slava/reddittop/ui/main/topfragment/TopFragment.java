package com.slava.reddittop.ui.main.topfragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.slava.reddittop.R;
import com.slava.reddittop.model.top.RedditTop;

import com.slava.reddittop.ui.base.BaseFragment;
import com.slava.reddittop.ui.base.PaginationScrollListener;

import butterknife.BindView;

public class TopFragment extends BaseFragment implements TopMvp.View, TopAdapter.RecyclerViewClickListener {

    public static int LAYOUT = R.layout.fragment_top;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeLayout;
    private boolean isLastPage;
    private int currentPage = 1;
    private int totalPages = 5;
    private String after;
    private boolean isLoading = true;
    private TopAdapter topAdapter;
    private TopPresenter presenter;

    @Override
    public int getLayout() {
        return LAYOUT;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            presenter = new TopPresenter();
            presenter.attachView(TopFragment.this);
        }
        initRv();
        swipeLayout.setOnRefreshListener(() -> {
            topAdapter.clearList();
            currentPage = 1;
            isLastPage = false;
            presenter.getRedditTop(null, 10);
        });
        topAdapter.addLoadingFooter();
        presenter.getRedditTop(null, 10);
    }

    /**
     * initialize recycler view;
     */
    void initRv() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        topAdapter = new TopAdapter(this);
        recyclerView.setAdapter(topAdapter);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        PaginationScrollListener scrollListener = new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                if (currentPage == totalPages) isLastPage = true;
                recyclerView.post(() -> topAdapter.addLoadingFooter());
                presenter.getRedditTop(after, 10);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }



    /**
     * @return instance of this fragment;
     */
    public static TopFragment newInstance() {
        TopFragment myFragment = new TopFragment();
        Bundle args = new Bundle();
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void recyclerViewListClicked(View v, String link) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(v.getContext(), Uri.parse(link));
    }


    @Override
    public void showRedditTopPosts(RedditTop redditTop) {
        isLoading = false;
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
        after = redditTop.getData().getAfter();
        topAdapter.handleResponse(redditTop.getData());
    }

    @Override
    public void handleError() {
        isLoading = false;
        if (swipeLayout.isRefreshing()) {
            topAdapter.clearList();
            swipeLayout.setRefreshing(false);
            Snackbar.make(swipeLayout, R.string.error, Snackbar.LENGTH_LONG)
                    .show();
        } else {
            currentPage--;
            topAdapter.removeLoadingFooter();
        }

    }
}
