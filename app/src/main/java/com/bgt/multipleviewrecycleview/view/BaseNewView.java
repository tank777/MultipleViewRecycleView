package com.bgt.multipleviewrecycleview.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bgt.multipleviewrecycleview.R;
import com.bgt.multipleviewrecycleview.model.FeedData;


public abstract class BaseNewView extends LinearLayout {

    protected boolean inWebview = false;

    public BaseNewView(Context context) {
        this(context, null);
    }

    public BaseNewView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseNewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
    }

    public abstract void setNew(FeedData feedData, boolean inWebview);
    public abstract void setViewId(int viewId);

}
