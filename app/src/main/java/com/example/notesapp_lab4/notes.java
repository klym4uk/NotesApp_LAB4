package com.example.notesapp_lab4;

import android.os.Parcel;
import android.os.Parcelable;

public class notes implements Parcelable {
    String Name;
    String Desc;

    public notes(String name, String desc) {
        Name = name;
        Desc = desc;
    }

    protected notes(Parcel in) {
        Name = in.readString();
        Desc = in.readString();
    }

    public static final Creator<notes> CREATOR = new Creator<notes>() {
        @Override
        public notes createFromParcel(Parcel in) {
            return new notes(in);
        }

        @Override
        public notes[] newArray(int size) {
            return new notes[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Desc);
    }
}
