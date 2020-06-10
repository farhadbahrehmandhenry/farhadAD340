package com.example.farhad;


import android.os.Parcel;
import android.os.Parcelable;

public class MatchItem implements Parcelable {
    public String uid;
    public String name;
    public String imageUrl;
    public boolean liked;
    public String lat;
    public String longitude;

    public MatchItem() {}

    public MatchItem(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        liked = in.readByte() != 0;
        lat = in.readString();
        longitude = in.readString();
    }

    public static final Creator<MatchItem> CREATOR = new Creator<MatchItem>() {
        @Override
        public MatchItem createFromParcel(Parcel in) {
            return new MatchItem(in);
        }

        @Override
        public MatchItem[] newArray(int size) {
            return new MatchItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(imageUrl);
        parcel.writeByte((byte) (liked ? 1 : 0));
        parcel.writeString(lat);
        parcel.writeString(longitude);

    }
}
