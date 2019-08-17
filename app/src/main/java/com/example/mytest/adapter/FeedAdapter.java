package com.example.mytest.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytest.R;
import com.example.mytest.interfase.ItemClicListner;
import com.example.mytest.rssfeed.RSSObject;


public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    private RSSObject rssObject;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewGroup parent;


    public FeedAdapter(RSSObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.row, parent, false);
        return new FeedViewHolder(itemView);
    }

    public FeedAdapter(RSSObject rssObject, Context mContext, LayoutInflater layoutInflater, ViewGroup parent) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        this.layoutInflater = layoutInflater;
        this.parent = parent;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder feedViewHolder, int pos) {
        feedViewHolder.textTitle.setText(rssObject.getItems().get(pos).getTitle());
        feedViewHolder.textPudData.setText(rssObject.getItems().get(pos).getPubDate());
        feedViewHolder.content.setText(rssObject.getItems().get(pos).getContent());

        feedViewHolder.setItemClicListner(new ItemClicListner() {

            @Override
            public void onClic(View view, int position, boolean isLongClick) {
                if (!isLongClick){
                    Intent brIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
                    mContext.startActivity(brIntent);
                }
            }

        });

    }

    @Override
    public int getItemCount() {
        return rssObject.items.size();
    }
}

