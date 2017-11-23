package com.example.tranh.wikiapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.bitmap;

/**
 * Created by tranh on 9/25/2017.
 */

public class WikiAppFragment extends Fragment {

    private static RecyclerView mWikiAppView;
    private List<ArticleItem> mItems = new ArrayList<>();
    private ThumbnailDownloader<ArticleHolder> mThumbnailDownloader;

    private static final String TAG = "WikiAppFragment";

    public static WikiAppFragment newInstance(){
        return new WikiAppFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemTask().execute();

        Handler responseHandler = new Handler();
        mThumbnailDownloader = new ThumbnailDownloader<>(responseHandler);
        mThumbnailDownloader.setThumbnailDownloadListener(
                new ThumbnailDownloader.ThumbnailDownloadListener<ArticleHolder>(){
                    @Override
                    public void onThumbnailDownloaded(ArticleHolder articleHolder, Bitmap bitmap) {
                    Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                    articleHolder.bindDrawable(drawable);
            }
        }
        );
        mThumbnailDownloader.start();
        mThumbnailDownloader.getLooper();
        Log.i(TAG, "Background thread started");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wiki_app, container, false);
        mWikiAppView = (RecyclerView) v
                .findViewById(R.id.fragment_wiki_app_recycler_view);
        mWikiAppView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        setupAdapter();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailDownloader.clearQueue();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailDownloader.quit();
        Log.i(TAG, "Background thread destroyed");
    }

    //Setup adapter for Wiki App View
    private void setupAdapter() {
        if (isAdded()) {
            mWikiAppView.setAdapter(new ArticleAdapter(mItems));
        }
    }

    private class ArticleHolder extends RecyclerView.ViewHolder {
            private ImageView mArticleImageView;
        public ArticleHolder(View itemView) {
            super(itemView);
            mArticleImageView = (ImageView) itemView
            .findViewById(R.id.fragment_article_app_image_view);
        }
//        public void bindWikiItem(ArticleItem item) {
//            mTitleTextView.setText(item.toString());
//        }
        public void bindDrawable(Drawable drawable){
            mArticleImageView.setImageDrawable(drawable);
        }
    }

    private class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {
        private List<ArticleItem> mWikiItems;
        public ArticleAdapter(List<ArticleItem> wikiItems) {
            mWikiItems = wikiItems;
        }
        @Override
        public ArticleHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//            TextView textView = new TextView(getActivity());
//            return new ArticleHolder(textView);

            /*Create inflater and inflate view, then put created view in holder*/
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.article_item, viewGroup, false);
            return new ArticleHolder(view);
        }
        @Override
        public void onBindViewHolder(ArticleHolder articleHolder, int position) {
            ArticleItem galleryItem = mWikiItems.get(position);
            //articleHolder.bindWikiItem(galleryItem);
            /*Bind default image*/
            Drawable placeholder = getResources().getDrawable(R.drawable.load_symbol);
            articleHolder.bindDrawable(placeholder);
            mThumbnailDownloader.queueThumbnail(articleHolder, galleryItem.getmImageUrl());
        }
        @Override
        public int getItemCount() {
            return mWikiItems.size();
        }
    }

    private class FetchItemTask extends AsyncTask<Void,Void,List<ArticleItem>>{
        @Override
        protected List<ArticleItem> doInBackground(Void... params){
            return new WikiFetchr().fetchItems();
        }
        @Override
        protected void onPostExecute(List<ArticleItem> items) {
            mItems = items;
            setupAdapter();
        }
    }
}
