package com.prituladima.collectionmapsarchexample.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.prituladima.collectionmapsarchexample.R;
import com.prituladima.collectionmapsarchexample.view.adapters.TabsAdapter;

import butterknife.BindView;

import static butterknife.ButterKnife.bind;

public final class MainActivity extends AppCompatActivity {

  @BindView(R.id.main_tabs)
  TabLayout tabLayout;

  @BindView(R.id.view_pager)
  ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    bind(this);

    TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager());
    viewPager.setAdapter(tabsAdapter);
    tabLayout.setupWithViewPager(viewPager);
  }
}
