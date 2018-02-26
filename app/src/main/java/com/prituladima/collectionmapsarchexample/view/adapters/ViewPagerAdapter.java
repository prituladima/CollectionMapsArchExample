package com.prituladima.collectionmapsarchexample.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.prituladima.collectionmapsarchexample.view.fragments.CollectionsFragment;
import com.prituladima.collectionmapsarchexample.view.fragments.MapsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new CollectionsFragment();
            case 1:
                return new MapsFragment();
            default:
                return new CollectionsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle (int position) {
        switch (position) {
            case 0:
                return "Collections";
            case 1:
                return "Maps";
        }
        return super.getPageTitle(position);
    }
}