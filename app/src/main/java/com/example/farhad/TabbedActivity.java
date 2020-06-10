package com.example.farhad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.common.internal.Constants;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TabbedActivity extends AppCompatActivity implements OnListFragmentInteractionListener {
    private MatchViewModel viewModel;
    private static final String TAG = TabbedActivity.class.getName();
    private Bundle matchesBundle;
    private boolean matchesRetrieved = false;
    private ArrayList<MatchItem> matches;
    private SettingsViewModel mSettingsViewModel;
    private int matchDistance = 10;
    LocationManager locationManager;
    double longitudeGPS, latitudeGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mSettingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        viewModel = new MatchViewModel();
        Intent mainIntent = getIntent();
        Bundle bundleIntent = mainIntent.getExtras();

        matchesBundle = new Bundle();
        viewModel.getMatchItems((ArrayList<MatchItem> matchItems) -> {
            matches = matchItems;
            Iterator<MatchItem> matchItemIterator = matches.iterator();
            if (longitudeGPS != 0.0) {
                while (matchItemIterator.hasNext()) {
                    MatchItem match = matchItemIterator.next();
                    float[] mDistanceResults = new float[3];
                    Location.distanceBetween(latitudeGPS, longitudeGPS, Float.parseFloat(match.lat),
                            Float.parseFloat(match.longitude), mDistanceResults);
                    double mDistanceMiles = mDistanceResults[0] / 1609.34;
                    if (mDistanceMiles > 10) {
                        matchItemIterator.remove();
                    }
                }}
            matchesBundle.putParcelableArrayList(Constant.KEY_MATCHES, matches);
            matchesRetrieved = true;
        });
        requestUpdateGPS();
        MatchesFragment matchesFragment = new MatchesFragment();
        ProfileFragment profileFragment = new ProfileFragment();
        SettingsFragment settingsFragment = new SettingsFragment();

        profileFragment.setArguments(bundleIntent);
        ViewPager2 viewPager = findViewById(R.id.view);
        TabLayout tabLayout = findViewById(R.id.tabs);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        matchesFragment.setArguments(matchesBundle);
        viewPagerAdapter.addFragment(profileFragment);
        viewPagerAdapter.addFragment(matchesFragment);
        viewPagerAdapter.addFragment(settingsFragment);

        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText(Constant.KEY_PROFILE);
                    } else if (position == 1) {
                        tab.setText(Constant.KEY_MATCHESTAB);
                    } else {
                        tab.setText(Constant.KEY_SETTINGS);
                    }
                }
        ).attach();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.enable_location)
                .setMessage(getString(R.string.location_message))
                .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(myIntent, Constant.GET_LOCATION_SETTINGS);
                })
                .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {});
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        requestUpdateGPS();
        viewModel.getMatchItems((ArrayList<MatchItem> matchItems) -> {
            matches = matchItems;
            Iterator<MatchItem> matchItemIterator = matches.iterator();
            while (matchItemIterator.hasNext()) {
                MatchItem match = matchItemIterator.next();
                float[] mDistanceResults = new float[3];
                Location.distanceBetween(latitudeGPS, longitudeGPS, Float.parseFloat(match.lat),
                        Float.parseFloat(match.longitude), mDistanceResults);
                double mDistanceMiles = mDistanceResults[0] / 1609.34;
                if (mDistanceMiles > 10) {
                    matchItemIterator.remove();
                }
            }
            matchesBundle.putParcelableArrayList(Constant.KEY_MATCHES, matches);
            matchesRetrieved = true;
        });
    }

    public void requestUpdateGPS() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListenerGPS);
        } else {
            showAlert();
        }
    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.clear();
    }

    @Override
    public void onListFragmentInteraction(MatchItem item) {
        item.liked = !item.liked;
        viewModel.updateMatchItem(item);
    }

    public static class ViewPagerAdapter extends FragmentStateAdapter {

        private List<Fragment> fragments = new ArrayList<>();

        ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }
}
