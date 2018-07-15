package com.mfathy.mutilites.widgets;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Mohammed Fathy on 13/07/2018.
 * dev.mfathy@gmail.com
 */

public class HorizontalLayoutManager extends LinearLayoutManager{

    private boolean isScrollEnabled = true;

    public HorizontalLayoutManager(Context context) {
        super(context);
    }

    public HorizontalLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public HorizontalLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    /**
     * Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
     * @return true if you can scroll horizontally.
     */
    @Override
    public boolean canScrollHorizontally() {
        return isScrollEnabled && super.canScrollHorizontally();
    }
}
