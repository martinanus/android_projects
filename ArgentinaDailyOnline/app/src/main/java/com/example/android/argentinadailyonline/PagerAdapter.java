package com.example.android.argentinadailyonline;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static final String CATEGORY_KEY = "category_key";
    private static final int BUSINESS = 0;
    private static final int POLITICS = 1;
    private static final int TECHNOLOGY = 2;
    private static final int  WORLD= 3;


    private int mNumTabs;

    public  PagerAdapter(FragmentManager fm, int numTabs){
        super(fm);
        this.mNumTabs = numTabs;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position){
            case BUSINESS:
                return new FragmentBusiness();
            case POLITICS:
                return new FragmentPolitics();
            case TECHNOLOGY:
                return new FragmentTechnology();
            case WORLD:
                return new FragmentWorld();
            default: return null;
        }
    }




    /*
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        Fragment fragment = new Fragments();

        switch (position){
            case BUSINESS:
                bundle.putInt(CATEGORY_KEY,BUSINESS);
                break;
            case POLITICS:
                bundle.putInt(CATEGORY_KEY, POLITICS);
                break;
           case TECHNOLOGY:
               bundle.putInt(CATEGORY_KEY,TECHNOLOGY);
               break;
           case WORLD:
               bundle.putInt(CATEGORY_KEY, WORLD);
               break;
            default: return null;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

     */

    @Override
    public int getCount() {
        return this.mNumTabs;
    }
}
