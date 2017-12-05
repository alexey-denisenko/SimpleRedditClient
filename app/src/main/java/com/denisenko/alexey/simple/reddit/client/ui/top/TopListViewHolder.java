package com.denisenko.alexey.simple.reddit.client.ui.top;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denisenko.alexey.simple.reddit.client.R;
import com.denisenko.alexey.simple.reddit.client.entity.TopEntry;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.format.DateUtils.FORMAT_NUMERIC_DATE;

class TopListViewHolder extends RecyclerView.ViewHolder {

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

    private final Context context;

    TopListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
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
                        progressBar.setVisibility(View.GONE);
                    }
                });

        Resources res = context.getResources();
        int num = topEntry.getNumberOfComments();

        long time = TimeUnit.SECONDS.toMillis(topEntry.getCreatedAt());
        String timeAgo = DateUtils.getRelativeTimeSpanString(time,
                System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS,
                FORMAT_NUMERIC_DATE)
                .toString();

        String subtitleText = context.getString(R.string.top_list_submitted) +
                " " + timeAgo + " " +
                context.getString(R.string.top_list_by) + " " +
                topEntry.getAuthor();

        String numOfCommentsText = num + " " + res.getQuantityString(R.plurals.num_of_comments, num);

        title.setText(topEntry.getTitle());
        subtitle.setText(subtitleText);
        numOfComments.setText(numOfCommentsText);
    }
}
