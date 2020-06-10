package com.example.farhad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private static final String TAG = ProfileFragment.class.getSimpleName();

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView profName = view.findViewById(R.id.name);
        TextView profAgeLoc = view.findViewById(R.id.age);
        TextView profOcc = view.findViewById(R.id.occupation);
        TextView profDesc = view.findViewById(R.id.description);
        ImageView profilePic = view.findViewById(R.id.profileImage);

        Bundle bundle = getArguments();

        StringBuilder nameString = new StringBuilder(bundle.getString(Constant.KEY_NAME).trim());
        StringBuilder ageLocString = new StringBuilder(bundle.getString(Constant.KEY_AGE));
        StringBuilder occString = new StringBuilder(" ")
                .append(bundle.getString(Constant.KEY_OCCUPATION).trim());
        StringBuilder descString = new StringBuilder(bundle.getString(Constant.KEY_DESCRIPTION.trim()));

        profilePic.setImageResource(R.drawable.profile_pic);
        profName.setText(nameString);
        profAgeLoc.setText(ageLocString);
        profOcc.setText(occString);
        profDesc.setText(descString);

        return view;
    }
}