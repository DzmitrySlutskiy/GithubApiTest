package com.epam.githubapitest.model;

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
public class GitHubOwner implements Parcelable {
    @JsonProperty("id")
    private int mId;

    @JsonProperty("login")
    private String mLogin;

    @JsonProperty("avatar_url")
    private String mAvatarUrl;

    public GitHubOwner(){}

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

    public GitHubOwner(Parcel parcel) {
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
    public static final Creator<GitHubOwner> CREATOR = new Creator<GitHubOwner>() {
        @Override
        public GitHubOwner createFromParcel(Parcel parcel) {
            return new GitHubOwner(parcel);
        }

        @Override
        public GitHubOwner[] newArray(int i) {
            return new GitHubOwner[i];
        }
    };
}
