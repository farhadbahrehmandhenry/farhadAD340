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
        profileName = view.findViewById(R.id.name);
        profileAge = view.findViewById(R.id.age);
        profileOccupation = view.findViewById(R.id.occupation);
        profileDescription = view.findViewById(R.id.description);

        Bundle bundle=getArguments();

        name = bundle.getString(Constant.KEY_NAME);
        age = bundle.getString(Constant.KEY_AGE);
        occupation = bundle.getString(Constant.KEY_OCCUPATION);
        description = bundle.getString(Constant.KEY_DESCRIPTION);

        profileName.setText(name);
        profileAge.setText(age);
        profileOccupation.setText(occupation);
        profileDescription.setText(description);

        return view;
    }
}