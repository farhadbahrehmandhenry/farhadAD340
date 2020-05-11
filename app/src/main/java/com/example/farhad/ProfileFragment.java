package com.example.farhad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    TextView profileName;
    TextView profileAge;
    TextView profileOccupation;
    TextView profileDescription;
    String name;
    String age;
    String occupation;
    String description;

    private int position;

    public static ProfileFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        String[] data = ((ProfileActivity) getActivity()).getExtraData();

        profileName = view.findViewById(R.id.name);
        profileAge = view.findViewById(R.id.age);
        profileOccupation = view.findViewById(R.id.occupation);
        profileDescription = view.findViewById(R.id.description);

        profileName.setText(data[0]);
        profileAge.setText(data[1]);
        profileOccupation.setText(data[2]);
        profileDescription.setText(data[3]);

        return view;
    }
}