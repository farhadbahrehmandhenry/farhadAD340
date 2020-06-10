package com.example.farhad;


import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MatchesFragment extends Fragment {
    private static final String TAG = MatchesFragment.class.getName();
    private List<MatchItem> mMatches;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private MatchAdapter rcvAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMatches = getArguments().getParcelableArrayList(Constant.KEY_MATCHES);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMatches = getArguments().getParcelableArrayList(Constant.KEY_MATCHES);
        recyclerView.setAdapter(new MatchAdapter(mMatches, mListener));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.matches_fragment, container, false);
        rcvAdapter = new MatchAdapter(mMatches, mListener);
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(rcvAdapter);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnListFragmentInteractionListener) context;
    }
}