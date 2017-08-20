package com.bgt.multipleviewrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.bgt.multipleviewrecycleview.model.FeedData;
import com.bgt.multipleviewrecycleview.view.NewView;

import java.util.ArrayList;
import java.util.List;

public class FeedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> mData;
    private Context mContext;

    public FeedsAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new NewViewHolder(new NewView(mContext));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NewViewHolder && mData.get(position) instanceof FeedData) {
            ((NewViewHolder) holder).setViewId(position + 1);
            ((NewViewHolder) holder).setNew((FeedData) mData.get(position));
            if (((FeedData) mData.get(position)).isExclusive()) {
                ((NewViewHolder) holder).exclusiveIcon.setVisibility(View.VISIBLE);
                ((NewViewHolder) holder).relative_main.setPadding(0, 10, 0, 10);
            } else {
                ((NewViewHolder) holder).exclusiveIcon.setVisibility(View.GONE);
                ((NewViewHolder) holder).relative_main.setPadding(20, 20, 20, 20);
            }
        }
    }

    public void setNews(List<FeedData> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }
}