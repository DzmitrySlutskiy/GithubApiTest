package com.epam.dzmitry_slutski.githubapitest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Repository
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

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
}