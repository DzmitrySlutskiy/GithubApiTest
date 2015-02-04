package com.epam.dzmitry_slutski.githubapitest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Repository
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository implements Parcelable {

    @JsonProperty("id")
    private int mId;

    @JsonProperty("name")
    private String mName;

    @JsonProperty("full_name")
    private String mFullName;

    @JsonProperty("html_url")
    private String mHtmlUrl;

    @JsonProperty("description")
    private String mDescription;

    @JsonProperty("fork")
    private boolean mFork;

    @JsonProperty("owner")
    private Owner mOwner;

    @Override
    public String toString() {
        return mName + " " + mFullName + " " + mHtmlUrl + " " + mDescription;
    }

    public Repository(){
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getFullName() {
        return mFullName;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean ismFork() {
        return mFork;
    }

    public Owner getOwner() {
        return mOwner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Repository(Parcel parcel) {
        mId = parcel.readInt();
        mName = parcel.readString();
        mFullName = parcel.readString();
        mHtmlUrl = parcel.readString();
        mDescription = parcel.readString();
        mFork = parcel.readInt() != 0;
        mOwner = parcel.readParcelable(Owner.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mFullName);
        parcel.writeString(mHtmlUrl);
        parcel.writeString(mDescription);
        parcel.writeInt(mFork ? 1 : 0);
        parcel.writeParcelable(mOwner, 0);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel parcel) {
            return new Repository(parcel);
        }

        @Override
        public Repository[] newArray(int i) {
            return new Repository[i];
        }
    };
}