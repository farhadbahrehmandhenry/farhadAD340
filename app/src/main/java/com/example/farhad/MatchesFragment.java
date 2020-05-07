package com.example.farhad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class MatchesFragment extends Fragment {

    private int position;

    public MatchesFragment() {
        // Required empty public constructor
    }

    public static MatchesFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        MatchesFragment fragment = new MatchesFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }
}