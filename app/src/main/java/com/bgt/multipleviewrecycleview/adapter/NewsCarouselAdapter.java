package com.bgt.multipleviewrecycleview.adapter;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bgt.multipleviewrecycleview.model.FeedsImage;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class NewsCarouselAdapter extends PagerAdapter {
    private List<FeedsImage> mListImages;
    private Context context;

    public NewsCarouselAdapter(Context context, List<FeedsImage> mListImages) {
        this.context = context;
        this.mListImages = mListImages;
    }

    @Override
    public int getCount() {
        return mListImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(mListImages.get(position) != null && mListImages.get(position).getUrl() != null){
            Glide.with(context).load(mListImages.get(position).getUrl()).into(imageView);
            final String url=mListImages.get(position).getUrl();
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> images = new ArrayList<String>();
                    for (FeedsImage image : mListImages){
                        images.add(image.getUrl());
                    }
                    //ZoomImageCarouselFragment.newInstance(images,position).show(((FragmentActivity) context).getSupportFragmentManager(),null);
                }
            });
        }
        container.addView(imageView);
        return imageView;
    }

}
