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
public class GitHubRepository implements Parcelable {

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
    private GitHubOwner mGitHubOwner;

    @Override
    public String toString() {
        return mName + " " + mFullName + " " + mHtmlUrl + " " + mDescription;
    }

    public GitHubRepository(){
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

    public GitHubOwner getOwner() {
        return mGitHubOwner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public GitHubRepository(Parcel parcel) {
        mId = parcel.readInt();
        mName = parcel.readString();
        mFullName = parcel.readString();
        mHtmlUrl = parcel.readString();
        mDescription = parcel.readString();
        mFork = parcel.readInt() != 0;
        mGitHubOwner = parcel.readParcelable(GitHubOwner.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mFullName);
        parcel.writeString(mHtmlUrl);
        parcel.writeString(mDescription);
        parcel.writeInt(mFork ? 1 : 0);
        parcel.writeParcelable(mGitHubOwner, 0);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final Creator<GitHubRepository> CREATOR = new Creator<GitHubRepository>() {
        @Override
        public GitHubRepository createFromParcel(Parcel parcel) {
            return new GitHubRepository(parcel);
        }

        @Override
        public GitHubRepository[] newArray(int i) {
            return new GitHubRepository[i];
        }
    };
}