package com.example.mytest.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mytest.R;
import com.example.mytest.interfase.ItemClicListner;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    public TextView textTitle;
    public TextView textPudData;
    public TextView content;
    private ItemClicListner itemClicListner;

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);

        textTitle = itemView.findViewById(R.id.textTitle);
        textPudData = itemView.findViewById(R.id.textPudDate);
        content = itemView.findViewById(R.id.textContent);

        //setEvent

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }

    public void setItemClicListner(ItemClicListner itemClicListner) {
        this.itemClicListner = itemClicListner;
    }

    @Override
    public void onClick(View v) {
        itemClicListner.onClic(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClicListner.onClic(v,getAdapterPosition(),true);
        return true;
    }
}
