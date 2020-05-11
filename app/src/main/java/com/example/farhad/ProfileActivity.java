package com.example.farhad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ProfileActivity extends AppCompatActivity {
    String name;
    String age;
    String occupation;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SampleFragmentPagerAdapter adapter =new SampleFragmentPagerAdapter(getSupportFragmentManager(),ProfileActivity.this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void backToProfileDetail(View view) {
        finish();
    }

    public String[] getExtraData() {

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Bundle bundle = new Bundle();
        String[] data = new String[4];

        if (b != null) {
            if (b.containsKey(Constant.KEY_NAME)) {
//                bundle.putString(Constant.KEY_NAME, b.getString(Constant.KEY_NAME));
                data[0] = b.getString(Constant.KEY_NAME);
            }
            if (b.containsKey(Constant.KEY_AGE)) {
//                bundle.putString(Constant.KEY_AGE, b.getString(Constant.KEY_AGE));
                data[1] = b.getString(Constant.KEY_AGE);
            }
            if (b.containsKey(Constant.KEY_OCCUPATION)) {
//                bundle.putString(Constant.KEY_OCCUPATION, b.getString(Constant.KEY_OCCUPATION));
                data[2] = b.getString(Constant.KEY_OCCUPATION);
            }
            if (b.containsKey(Constant.KEY_DESCRIPTION)) {
//                bundle.putString(Constant.KEY_DESCRIPTION, b.getString(Constant.KEY_DESCRIPTION));
                data[3] = b.getString(Constant.KEY_DESCRIPTION);
            }
        }

        return data;
    }
}