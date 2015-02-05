package com.epam.dzmitry_slutski.githubapitest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Commiter
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubCommitter {
    @JsonProperty("name")
    private String mName;

    @JsonProperty("email")
    private String mMail;

    @JsonProperty("date")
    private String mDate;

    public String getName() {
        return mName;
    }

    public String getMail() {
        return mMail;
    }

    public String getDate() {
        return mDate;
    }
}
