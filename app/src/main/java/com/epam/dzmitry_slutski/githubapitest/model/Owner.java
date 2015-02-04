package com.epam.dzmitry_slutski.githubapitest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Owner
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {
    @JsonProperty("id")
    private int mId;

    @JsonProperty("login")
    private String mLogin;

    @JsonProperty("avatar_url")
    private String mAvatarUrl;


    public int getId() {
        return mId;
    }

    public String getLogin() {
        return mLogin;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }
}
