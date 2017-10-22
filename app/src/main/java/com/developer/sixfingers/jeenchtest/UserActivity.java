package com.developer.sixfingers.jeenchtest;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(R.id.tab_host);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("Albums").setIndicator(getString(R.string.albums), null),
                AlbumsFragment.class, null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("Posts").setIndicator(getString(R.string.posts), null),
                PostsFragment.class, null);

        GlobalData.getInstance().userId = getIntent().getIntExtra("userId", 0);
    }
}
