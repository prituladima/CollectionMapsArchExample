package com.prituladima.collectionmapsarchexample.view.adapters;

import android.support.v4.app.*;

import com.prituladima.collectionmapsarchexample.view.fragments.ListFragment;
import com.prituladima.collectionmapsarchexample.view.fragments.*;

public final class TabsAdapter extends FragmentStatePagerAdapter {

  public TabsAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new ListFragment();
      case 1:
        return new MapsFragment();
      default:
        return new ListFragment();
    }
  }

  @Override
  public int getCount() {
    return 2;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return "List";
      case 1:
        return "Maps";
    }
    return super.getPageTitle(position);
  }
}
