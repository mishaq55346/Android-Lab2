package com.example.lab2;

import android.os.Parcel;
import android.os.Parcelable;

class Tech implements Parcelable{
    String techName = "";
    String graphic = "";
    String helpText = "";

    Tech(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        techName = data[0];
        graphic = data[1];
        helpText = data[2];
    }

    Tech(String techName, String graphic, String helpText) {
        this.techName = techName;
        this.graphic = graphic;
        this.helpText = helpText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { techName, graphic, helpText });
    }

    public static final Parcelable.Creator<Tech> CREATOR = new Parcelable.Creator<Tech>() {

        @Override
        public Tech createFromParcel(Parcel source) {
            return new Tech(source);
        }

        @Override
        public Tech[] newArray(int size) {
            return new Tech[size];
        }
    };
}
