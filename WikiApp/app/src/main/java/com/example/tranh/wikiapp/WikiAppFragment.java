package com.example.tranh.wikiapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tranh on 9/25/2017.
 */

public class WikiAppFragment extends Fragment {

    private static RecyclerView mWikiAppView;

    public static WikiAppFragment newInstance(){
        return new WikiAppFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wiki_app, container, false);
        mWikiAppView = (RecyclerView) v
                .findViewById(R.id.fragment_wiki_app_recycler_view);
        mWikiAppView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return v;
    }
}
