package com.bgt.multipleviewrecycleview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bgt.multipleviewrecycleview.R;


public class ViewPagerIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {
    private Context mContext = null;
    private ViewPager mPager;

    @ColorInt
    private int mSelectedColor = -1;
    @ColorInt
    private int mDeselectedColor = -1;
    private int mIndicatorSpacing = 5;

    private ImageView mCurrentSelectedView;
    private int mCurrentSelectedIndex = 0;

    /**
     * Set this after setting the adapter to the pager.
     *
     * @param pager the connected viewpager
     */
    public void setPager(ViewPager pager) {
        if (mPager != null) {
            mPager.removeOnPageChangeListener(this);
            mPager = null;
        }

        mPager = pager;

        initializeIndicatorBar(mPager.getAdapter().getCount());
        mPager.addOnPageChangeListener(this);
        mPager.getAdapter().registerDataSetObserver(mDatasetObserver);

    }


    public ViewPagerIndicator(Context context) {
        super(context);
        initializeViews(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context, attrs);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context, attrs);
    }


    private void initializeViews(Context context, AttributeSet attrs) {
        mContext = context;

        setGravity(Gravity.CENTER);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);

            mSelectedColor = a.getColor(R.styleable.ViewPagerIndicator_indicatorSelectedColor, getThemeColor(context, R.attr.colorAccent));
            mDeselectedColor = a.getColor(R.styleable.ViewPagerIndicator_indicatorDeselectedColor, Color.WHITE);
            mIndicatorSpacing = (int) a.getDimension(R.styleable.ViewPagerIndicator_indicatorSpacing, 5);

            a.recycle();
        }
    }

    @ColorInt
    private int getThemeColor(@NonNull final Context context, @AttrRes final int attributeColor) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attributeColor, value, true);

        return value.data;
    }

    private void initializeIndicatorBar(int num) {
        removeAllViewsInLayout();

        for (int i = 0; i < num; i++) {
            ImageView img = new ImageView(mContext);

            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp.setMargins(mIndicatorSpacing / 2, 0, mIndicatorSpacing / 2, 0);
            lp.gravity = Gravity.CENTER;
            img.setScaleX(0.5f);
            img.setScaleY(0.5f);
            addView(img, lp);
        }

        setSelectedIndicator(mPager.getCurrentItem());
    }

    private void setSelectedIndicator(int selected) {
        int num = getChildCount();

        for (int i = 0; i < num; i++) {
            ImageView img = (ImageView) getChildAt(i);

            img.clearColorFilter();


            img.setImageResource(R.drawable.circle_drawable);
            img.setColorFilter(new LightingColorFilter(0, mDeselectedColor));
        }

        if(mCurrentSelectedView != null){
            mCurrentSelectedView.setScaleX(0.5f);
            mCurrentSelectedView.setScaleY(0.5f);
        }
        mCurrentSelectedView = (ImageView) getChildAt(selected);

        if (mCurrentSelectedView != null) {
            mCurrentSelectedView.setScaleX(1f);
            mCurrentSelectedView.setScaleY(1f);
            mCurrentSelectedView.setColorFilter(new LightingColorFilter(0, mSelectedColor));
        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(mCurrentSelectedView != null){
            mCurrentSelectedView.setScaleX(Math.max(0.5f, mCurrentSelectedIndex == position ?
                    1 - positionOffset : positionOffset));
            mCurrentSelectedView.setScaleY(Math.max(0.5f, mCurrentSelectedIndex == position ?
                    1 - positionOffset : positionOffset));
        }

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentSelectedIndex = position;
        setSelectedIndicator(mCurrentSelectedIndex);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    private DataSetObserver mDatasetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            initializeIndicatorBar(mPager.getAdapter().getCount());
        }
    };
}
