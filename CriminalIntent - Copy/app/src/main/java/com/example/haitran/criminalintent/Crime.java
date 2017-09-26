package com.example.haitran.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hai Tran on 3/12/2017.
 */

public class Crime {
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    private boolean mSolved;
    private Date mDate;
    private String mTitle;
    private UUID mId;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }


}
