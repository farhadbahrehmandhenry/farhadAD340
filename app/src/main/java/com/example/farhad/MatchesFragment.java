package com.example.farhad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {

    private int position;
    List<Matches> matchesList;

    public MatchesFragment() {

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
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        matchesList = new ArrayList<>();
        matchesList.add(new Matches("beautiful giraff", "animal", "beautiful", R.drawable.b));
        matchesList.add(new Matches("handsome owl", "animal", "handsome", R.drawable.d));

        RecyclerView myrv = view.findViewById(R.id.recyclyView);
        RecycleViewAdapter myAdapter = new RecycleViewAdapter(getActivity(), matchesList);
        myrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrv.setAdapter((myAdapter));

        return view;
    }
}