package com.example.farhad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SettingsFragment extends Fragment {
    private int position;
    public static final int ADD_SETTINGS_REQUEST = 1;
    private SettingViewModel settingsviewModel;

    public SettingsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        SettingsFragment fragment = new SettingsFragment();
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
        settingsviewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_settings, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

//        FloatingActionButton buttonAddNote = recyclerView.findViewById(R.id.add);
//        buttonAddNote.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), AddSettingsActivity.class);
//
//                startActivityForResult(intent, ADD_SETTINGS_REQUEST);
//            }
//        });


        final SettingsAdapter adapter = new SettingsAdapter();
        recyclerView.setAdapter(adapter);
        settingsviewModel = new ViewModelProvider(this).get(SettingViewModel.class);

        settingsviewModel.getAllSettings().observe(getViewLifecycleOwner(), new Observer<List<Settings>>() {
            @Override
            public void onChanged(List<Settings> settings) {
                adapter.setSettings(settings);
            }
        });

        adapter.setOnItemClickListener(new SettingsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Settings settings) {
//                Intent intent = new Intent(this, AddEditNoteActivity.class);
//
//                intent.putExtras(AddEditSettingsActivity.EXTRA_REMINDER, settings.getAccountType());
            }
        });

        return recyclerView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_SETTINGS_REQUEST && resultCode == RESULT_OK) {
            int reminder = data.getIntExtra(AddSettingsActivity.Extra_REMINDER, 0);
            int distance = data.getIntExtra(AddSettingsActivity.Extra_DISTANCE, 1);
            int gender = data.getIntExtra(AddSettingsActivity.Extra_GENDER, 0);
            int account = data.getIntExtra(AddSettingsActivity.Extra_ACCOUNT, 0);
            int age = data.getIntExtra(AddSettingsActivity.Extra_AGEGENDER, 18);

            Settings settings = new Settings(reminder, distance, gender, account, age);
            settingsviewModel.insert(settings);

            Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "not saved", Toast.LENGTH_SHORT).show();
        }
    }
}