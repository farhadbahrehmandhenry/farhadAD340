package com.example.farhad;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        switch(position) {
            case 0:
                f = ProfileFragment.newInstance(position);
                break;
            case 1:
                f = MatchesFragment.newInstance(position);
                break;
            case 2:
                f = SettingsFragment.newInstance(position);
                break;
        }
        
        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Profile";
        }

        if (position == 1) {
            return "Matches";
        }

        if (position == 2) {
            return "Settings";
        }

        return null;
    }
}
