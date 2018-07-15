package com.mfathy.mutilites.widgets;

import android.support.design.widget.AppBarLayout;

/**
 * Created by Mohammed Fathy on 14/07/2018.
 * dev.mfathy@gmail.com
 *
 * Use this Listener class to get Collapse| Expand events from {@link android.support.design.widget.CollapsingToolbarLayout} or {@link AppBarLayout}
 * Called when the {@link AppBarLayout}'s layout offset has been changed. This allows
 * child views to implement custom behavior based on the offset (for instance pinning a
 * view at a certain y value).
 *
 */

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
    // State
    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED, i);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED, i);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE, i);
            }
            mCurrentState = State.IDLE;
        }
    }

    /**
     * Notifies when the {@link AppBarLayout}'s layout offset has been changed
     * @param appBarLayout the {@link AppBarLayout} which offset has changed
     * @param verticalOffset the vertical offset for the parent {@link AppBarLayout}, in px
     * @param state         Collapse state
     */
    public abstract void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset);
}
