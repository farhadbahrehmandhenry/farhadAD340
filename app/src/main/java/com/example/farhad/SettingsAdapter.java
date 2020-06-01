package com.example.farhad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsHolder> {
    private List<Settings> settings = new ArrayList<>();
    private onItemClickListener listener;

    @NonNull
    @Override
    public SettingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.setting_item, parent, false);
        return new SettingsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsHolder holder, int position) {
        Settings currentSettings = settings.get(position);
        holder.reminder.setText(String.valueOf(currentSettings.getDailyMatchesReminderTime()));
        holder.distance.setText(String.valueOf(currentSettings.getMaximumDistanceSearch()));
        holder.gender.setText(String.valueOf(currentSettings.getGender()));
        holder.account.setText(String.valueOf(currentSettings.getAccountType()));
        holder.ageRange.setText(String.valueOf(currentSettings.getAgeRange()));
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }

    public void setSettings(List<Settings> settings) {
        this.settings = settings;
        notifyDataSetChanged();
    }

    class SettingsHolder extends RecyclerView.ViewHolder {
        private TextView reminder;
        private TextView distance;
        private TextView gender;
        private TextView account;
        private TextView ageRange;

        public SettingsHolder(@NonNull View itemView) {
            super(itemView);
            reminder = itemView.findViewById(R.id.reminder);
            distance = itemView.findViewById(R.id.distance);
            gender = itemView.findViewById(R.id.gender);
            account = itemView.findViewById(R.id.accountType);
            ageRange = itemView.findViewById(R.id.ageRange);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(settings.get(position));
                    }
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(Settings settings);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
