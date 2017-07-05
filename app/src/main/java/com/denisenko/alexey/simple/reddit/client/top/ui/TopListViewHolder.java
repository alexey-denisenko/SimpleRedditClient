package com.denisenko.alexey.simple.reddit.client.top.ui;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisenko.alexey.simple.reddit.client.R;
import com.denisenko.alexey.simple.reddit.client.top.TopEntry;
import com.github.kevinsawicki.timeago.TimeAgo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.top_list_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.top_list_image)
    ImageView image;

    @BindView(R.id.top_list_title)
    TextView title;

    @BindView(R.id.top_list_subtitle)
    TextView subtitle;

    @BindView(R.id.top_list_num_comments)
    TextView numOfComments;

    private Context context;

    TopListViewHolder(View itemView, OnEntryClickListener onTopEntryClick) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();

        itemView.setOnClickListener(v -> onTopEntryClick.onEntryClick());
    }

    void bind(TopEntry topEntry) {
        Picasso.with(context)
                .load(topEntry.getThumbnailUrl())
                .error(R.mipmap.ic_launcher_round)
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        Resources res = context.getResources();
        int num = topEntry.getNumberOfComments();

        long time = topEntry.getCreatedAt();
        long current = new Date().getTime();
        String timeAgo = new TimeAgo().timeAgo(current - time);

        String subtitleText = context.getString(R.string.top_list_submitted) +
                timeAgo +
                context.getString(R.string.top_list_by) +
                topEntry.getAuthor();

        String numOfCommentsText = num + " " + res.getQuantityString(R.plurals.num_of_comments, num);

        title.setText(topEntry.getTitle());
        subtitle.setText(subtitleText);
        numOfComments.setText(numOfCommentsText);
    }


    public interface OnEntryClickListener {
        void onEntryClick();
    }
}
