package com.example.tranh.wikiapp;

/**
 * Created by tranh on 9/27/2017.
 */

public class ArticleItem {
    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmUrl() {
        return mUrl;
    }

    private String mTitle;
    private String mAuthor;
    private String mUrl;

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    private String mImageUrl;

    @Override
    public String toString(){
        return mTitle;
    }
}
