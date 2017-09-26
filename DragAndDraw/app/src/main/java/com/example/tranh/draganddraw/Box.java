package com.example.tranh.draganddraw;

import android.graphics.PointF;

/**
 * Created by tranh on 6/16/2017.
 */

public class Box {
    public void setmCurrent(PointF mCurrent) {
        this.mCurrent = mCurrent;
    }

    public PointF getmOrigin() {
        return mOrigin;
    }

    public PointF getmCurrent() {
        return mCurrent;
    }

    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF origin){
        mOrigin = origin;
        mCurrent = origin;
    }
}
