package com.example.farhad;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="setting_table")
public class Settings {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int dailyMatchesReminderTime;
    private int maximumDistanceSearch;
    private int gender;
    private int accountType;
    private int ageRange;

    public Settings(int dailyMatchesReminderTime, int maximumDistanceSearch, int gender, int accountType, int ageRange) {
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

    public int getGender() {
        return gender;
    }

    public int getAccountType() {
        return accountType;
    }

    public int getAgeRange() {
        return ageRange;
    }
}
