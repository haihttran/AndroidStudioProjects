package com.example.tranh.photogallery;

import android.net.Uri;

/**
 * Created by tranh on 5/10/2017.
 */

public class GalleryItem {
    public String getmCaption() {
        return mCaption;
    }

    public String getmId() {
        return mId;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    private String mCaption;
    private String mId;
    private String mUrl;

    public String getmOwner() {
        return mOwner;
    }

    public void setmOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    public Uri getPhotoPageUri(){
        return Uri.parse("http://www.flickr.com/photos")
                .buildUpon()
                .appendPath(mOwner)
                .appendPath(mId)
                .build();
    }

    private String mOwner;

    @Override
    public String toString(){
        return mCaption;
    }
}
