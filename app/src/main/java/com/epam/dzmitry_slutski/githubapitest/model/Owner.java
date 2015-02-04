package com.epam.dzmitry_slutski.githubapitest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Owner
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner implements Parcelable {
    @JsonProperty("id")
    private int mId;

    @JsonProperty("login")
    private String mLogin;

    @JsonProperty("avatar_url")
    private String mAvatarUrl;

    public Owner(){}

    public int getId() {
        return mId;
    }

    public String getLogin() {
        return mLogin;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Owner(Parcel parcel) {
        mId = parcel.readInt();
        mLogin = parcel.readString();
        mAvatarUrl = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mId);
        parcel.writeString(mLogin);
        parcel.writeString(mAvatarUrl);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel parcel) {
            return new Owner(parcel);
        }

        @Override
        public Owner[] newArray(int i) {
            return new Owner[i];
        }
    };
}
