package com.example.farhad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),ProfileActivity.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Bundle bundle = new Bundle();

        if (b != null) {
            if (b.containsKey(Constant.KEY_NAME)) {
                bundle.putString(Constant.KEY_NAME, b.getString(Constant.KEY_NAME));
            }
            if (b.containsKey(Constant.KEY_AGE)) {
                bundle.putString(Constant.KEY_AGE, b.getString(Constant.KEY_AGE));
            }
            if (b.containsKey(Constant.KEY_OCCUPATION)) {
                bundle.putString(Constant.KEY_OCCUPATION, b.getString(Constant.KEY_OCCUPATION));
            }
            if (b.containsKey(Constant.KEY_DESCRIPTION)) {
                bundle.putString(Constant.KEY_DESCRIPTION, b.getString(Constant.KEY_DESCRIPTION));
            }
        }

        ProfileFragment profile = new ProfileFragment();
        profile.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction t  = manager.beginTransaction();
//
//        t.add(R.id.frag, profile);
//        t.commit();
    }

    public void backToProfileDetail(View view) {
        finish();
    }
}