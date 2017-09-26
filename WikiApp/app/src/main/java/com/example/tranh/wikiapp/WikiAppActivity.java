package com.example.tranh.wikiapp;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WikiAppActivity extends SingleFragmentActivity {

    @Override
   public Fragment createFragment(){
        return WikiAppFragment.newInstance();
    }
}
