package com.bgt.multipleviewrecycleview.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bgt.multipleviewrecycleview.R;
import com.bgt.multipleviewrecycleview.adapter.NewsCarouselAdapter;
import com.bgt.multipleviewrecycleview.model.FeedData;
import com.bgt.multipleviewrecycleview.model.FeedsImage;
import com.bgt.multipleviewrecycleview.model.NewType;
import com.bgt.multipleviewrecycleview.utils.SizeUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class NewView extends BaseNewView {
    @BindView(R.id.pager_new_images)
    ViewPager pagerNewImages;
    @BindView(R.id.indicator_new_carousel)
    ViewPagerIndicator indicatorNewCarousel;
    @BindView(R.id.img_new)
    ImageView imgNew;
    @BindView(R.id.tv_new_date)
    TextView tvNewDate;
    @BindView(R.id.tv_new_title)
    TextView tvNewTitle;
    @BindView(R.id.tv_new_description)
    TextView tvNewDescription;

    @BindView(R.id.relative_main)
    RelativeLayout relative_main;

    private boolean mIsInitializing = false;


    private FeedData feedData;


    public NewView(Context context) {
        this(context, null);
    }

    public NewView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.view_new, this);
        ButterKnife.bind(this);

        pagerNewImages.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                SizeUtils.getDefaultImageHeight(context)));

    }


    @Override
    public void setNew(FeedData feedData, boolean inWebview) {
        this.feedData = feedData;
        this.inWebview = inWebview;

        imgNew.setImageBitmap(null);
        indicatorNewCarousel.setVisibility(GONE);
        pagerNewImages.setVisibility(GONE);
        imgNew.setVisibility(GONE);

        if (this.feedData != null) {
            //tvNewDate.setText(DateUtils.getDateToday(feedData.getDate(), "yyyy-MM-dd'T'HH:mm:ss"));
            tvNewTitle.setText(feedData.getTitle());
            tvNewDescription.setText(feedData.getExcerpt());
            handleNewType();
        }
    }

    @Override
    public void setViewId(int viewId) {

    }

    /*@OnClick(R.id.ll_share)
    public void shareNew() {
        if (getContext() instanceof FragmentActivity && feedData != null) {
            CABaseDialog.showDialog(((FragmentActivity) getContext()).getSupportFragmentManager(),
                    ShareDialog.newInstance(feedData), getContext().getString(R.string.dialog_share_title), null);
        }
    }*/




    private void handleNewType() {
        switch (NewType.getNewType(feedData)) {
            case NewType.SINGLE_PHOTO:
                imgNew.setVisibility(VISIBLE);
                imgNew.setScaleType(ImageView.ScaleType.CENTER_CROP);
                final FeedsImage image = feedData.getImages().get(0);
                measureImage(imgNew, image);
                if (image.getUrl() != null) {
                    Glide.with(getContext()).load(image.getUrl()).into(imgNew);
                    imgNew.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url=feedData.getImages().get(0).getUrl();
                            //ZoomImageFragment.newInstance(url).show(((FragmentActivity) getContext()).getSupportFragmentManager(),null);
                        }
                    });
                }
                else {
                    //imgNew.setImageResource(R.drawable.ic_placeholder_fallback);
                }
                break;
            case NewType.CAROUSEL:
                pagerNewImages.setVisibility(VISIBLE);
                indicatorNewCarousel.setVisibility(VISIBLE);
                pagerNewImages.setAdapter(new NewsCarouselAdapter(getContext(), feedData.getImages()));
                indicatorNewCarousel.setPager(pagerNewImages);
                break;
        }
    }


    private void measureImage(ImageView imageView, FeedsImage image) {
        if (image != null && image.getUrl() != null) {
            if (image.getHeight() != null && image.getWidth() != null
                    && image.getHeight() > 0 && image.getWidth() != null) {
                float aspectRatio = image.getWidth().floatValue() / image.getHeight().floatValue();
                imageView.getLayoutParams().height = SizeUtils.getImageHeight(getContext(), aspectRatio);
            } else {
                imageView.getLayoutParams().height = SizeUtils.getDefaultImageHeight(getContext());
            }
            imageView.requestLayout();
        }
    }
}
