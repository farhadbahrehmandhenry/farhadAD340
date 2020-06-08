package com.example.farhad;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity
public class Settings {
    @ColumnInfo(name = "max_distance")
    private static Integer MaxDistance;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    private String email = "";

    @ColumnInfo(name = "private_account")
    private boolean isPrivateAccount;

    @ColumnInfo(name = "reminder_hour")
    private Integer MatchReminderHour;

    @ColumnInfo(name = "reminder_min")
    private Integer MatchReminderMin;

//    private Integer MaxDistance;

    @ColumnInfo(name = "min_age")
    private static Integer MinAge;

    @ColumnInfo(name = "max_age")
    private static Integer MaxAge;

    @ColumnInfo(name = "gender")
    private static String Gender;

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isPrivateAccount() {
        return isPrivateAccount;
    }

    public void setPrivateAccount(boolean privateAccount) {
        isPrivateAccount = privateAccount;
    }

    public static Integer getMaxDistance() {
        return MaxDistance;
    }

    public void setMaxDistance(Integer maxDistance) {
        MaxDistance = maxDistance;
    }

    public Integer getMinAge() {
        return MinAge;
    }

    public static void setMinAge(Integer minAge) {
        MinAge = minAge;
    }

    public String getGender() {
        return Gender;
    }

    public static void setGender(String gender) {
        Gender = gender;
    }

    public Integer getMaxAge() {
        return MaxAge;
    }

    public static void setMaxAge(Integer maxAge) {
        MaxAge = maxAge;
    }

    public Integer getMatchReminderHour() {
        return MatchReminderHour;
    }

    public void setMatchReminderHour(Integer matchReminderHour) {
        MatchReminderHour = matchReminderHour;
    }

    public Integer getMatchReminderMin() {
        return MatchReminderMin;
    }

    public void setMatchReminderMin(Integer matchReminderMin) {
        MatchReminderMin = matchReminderMin;
    }
}