package com.bgt.multipleviewrecycleview.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.bgt.multipleviewrecycleview.R;
import com.bgt.multipleviewrecycleview.model.FeedData;
import com.bgt.multipleviewrecycleview.view.BaseNewView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.exclusive_icon)
    public LinearLayout exclusiveIcon;

    @BindView(R.id.relative_main)
    public RelativeLayout relative_main;

    public NewViewHolder(BaseNewView itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setNew(FeedData feedData) {
        setNew(feedData, false);
    }

    public void setNew(FeedData feedData, boolean inWebview) {
        ((BaseNewView) itemView).setNew(feedData, inWebview);
    }

    public void setViewId(int id){
        ((BaseNewView) itemView).setViewId(id);
    }
}
