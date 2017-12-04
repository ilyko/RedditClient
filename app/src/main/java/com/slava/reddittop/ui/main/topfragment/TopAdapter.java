package com.slava.reddittop.ui.main.topfragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.slava.reddittop.R;
import com.slava.reddittop.model.top.Child;
import com.slava.reddittop.model.top.Data;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * adapter for www.reddit.com/top.
 */
public class TopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Child> children;
    private RecyclerViewClickListener recyclerViewClickListener;

    private boolean isLoadingAdded = false;


    /**
     * class Constructor.
     * Creates empty list of objects. Binds click interface.
     *
     * @param recyclerViewClickListener listens for click on item in recyclerView;
     */
    TopAdapter(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.children = new ArrayList<>();
    }

    /**
     * Clears list of adapter.
     */
    void clearList() {
        children.clear();
        notifyDataSetChanged();
    }

    /**
     * adds items to recycler view which comes from request;
     *
     * @param data - our model from retrofit request;
     */
    void handleResponse(Data data) {
        removeLoadingFooter();
        children.addAll(data.getChildren());
        notifyDataSetChanged();
    }


    private enum TYPE {LOADING, POST}


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder vh = null;
        switch (TYPE.values()[viewType]) {
            case LOADING:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_loading, parent, false);
                vh = new LoadingViewHolder(v);
                vh.itemView.setOnClickListener(null);
                break;
            case POST:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_post, parent, false);
                vh = new RedditTopViewHolder(v);
                break;
        }
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == children.size() - 1 && isLoadingAdded) ? TYPE.LOADING.ordinal() : TYPE.POST.ordinal();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RedditTopViewHolder) {
            ((RedditTopViewHolder) holder).setItemTop(children.get(position));
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return children == null ? 0 : children.size();
    }


    /**
     * Adds progressbar in the bottom of the list.
     */
    void addLoadingFooter() {
        isLoadingAdded = true;
        children.add(new Child());
        notifyItemInserted(children.size() - 1);
    }

    /**
     * Removes progressbar from the bottom of the list.
     */
    void removeLoadingFooter() {
        isLoadingAdded = false;
        if (children.size() > 0) {
            int position = children.size() - 1;
            Child item = children.get(position);

            if (item != null) {
                children.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    /**
     * Class for progress bar in recyclerView;
     */

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progress)
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    /**
     * Class for RedditTop model.
     */
    class RedditTopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_rating)
        TextView tvRating;
        @BindView(R.id.tv_post_date)
        TextView tvPostDate;

        @BindView(R.id.img_thumbnail)
        ImageView imageView;

        RedditTopViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        /**
         * Fills viewholder with data from Reddit Top.
         *
         * @param child - contains: title, author, time post, thumbnail,
         * number of comments and upvotes.
         */
        void setItemTop(Child child) {
            tvTitle.setText(MessageFormat
                    .format("{0}. {1}", getAdapterPosition() + 1, child.getData().getTitle()));
            tvAuthor.setText(MessageFormat
                    .format("author: {0} in r/{1}", child.getData().getAuthor(), child.getData().getSubreddit()));
            long submittedTimeSeconds = child.getData().getCreatedUtc().longValue();
            long currentTimeMillis = System.currentTimeMillis();
            long hours = TimeUnit.SECONDS.toHours(currentTimeMillis / 1000 - submittedTimeSeconds);
            tvPostDate.setText(MessageFormat.format("submitted {0} hours ago", hours));
            tvRating.setText(MessageFormat.format("comments: {0}, rating: {1}",
                    child.getData().getNumComments(),
                    child.getData().getUps()));
            String path = child.getData().getThumbnail();
            if (TextUtils.isEmpty(path)) {
                imageView.setImageDrawable(null);
                return;
            }
            Glide.with(tvTitle.getContext())
                    .load(path)
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListener.recyclerViewListClicked(view,
                    children.get(getLayoutPosition()).getData().getUrl());
        }
    }

    public interface RecyclerViewClickListener {
        /**
         * on recycler item click method gives a link to activity
         * @param v - clicked view;
         * @param link - link with reddit post;
         */
        void recyclerViewListClicked(View v, String link);
    }
}
