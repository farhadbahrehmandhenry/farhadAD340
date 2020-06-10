package com.example.farhad;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "settings_database")
public class Settings {

    @NonNull
    @PrimaryKey
    private String email = "";

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    @NonNull
    @ColumnInfo(name = "distance")
    private int distance = 0;

    @NonNull
    public int getDistance() { return distance; };
    public void setDistance(@NonNull Integer distance) { this.distance = distance; }

    @ColumnInfo(name = "ageRange")
    private int ageRange = 0;

    public int getAgeRange() { return ageRange; }
    public void setAgeRange(int ageRange) { this.ageRange = ageRange; }

    @ColumnInfo(name = "reminder")
    private String reminder = "No Reminder";

    public String getReminder() { return reminder; }
    public void setReminder(String reminder) { this.reminder = reminder; }

    @ColumnInfo(name = "privacy")
    private String privacy = "";

    public String getPrivacy() { return privacy; }
    public void setPrivacy(String privacy) { this.privacy = privacy; }

    @Nullable
    @ColumnInfo(name = "gender")
    private String gender = "";

    public String getGender() { return gender; }
    public void setGender(@Nullable String gender) { this.gender = gender; }
}