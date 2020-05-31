package com.example.farhad;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="setting_table")
public class Settings {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int dailyMatchesReminderTime;
    private int maximumDistanceSearch;
    private String gender;
    private String accountType;
    private String ageRange;

    public Settings(int dailyMatchesReminderTime, int maximumDistanceSearch, String gender, String accountType, String ageRange) {
        this.dailyMatchesReminderTime = dailyMatchesReminderTime;
        this.maximumDistanceSearch = maximumDistanceSearch;
        this.gender = gender;
        this.accountType = accountType;
        this.ageRange = ageRange;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getDailyMatchesReminderTime() {
        return dailyMatchesReminderTime;
    }

    public int getMaximumDistanceSearch() {
        return maximumDistanceSearch;
    }

    public String getGender() {
        return gender;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAgeRange() {
        return ageRange;
    }
}
