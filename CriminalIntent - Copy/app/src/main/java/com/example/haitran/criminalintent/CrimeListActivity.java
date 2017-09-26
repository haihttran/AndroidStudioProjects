package com.example.haitran.criminalintent;

import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Hai Tran on 3/15/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.example.haitran.criminalintent.crime_id";
    private UUID currentCrimeId;

    @Override
    protected Fragment createFragment() {

        return new CrimeListFragment();

    }

    public UUID getCurrentCrimeId(){
        return currentCrimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
    }
}
