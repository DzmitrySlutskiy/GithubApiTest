package com.epam.dzmitry_slutski.githubapitest.model;

import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Commit
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {
    @JsonProperty("sha")
    private String mSha;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class CommitInfo {
        @JsonProperty("author")
        private Committer mAuthor;

        @JsonProperty("committer")
        private Committer mCommitter;

        @JsonProperty("message")
        private String mMessage;
    }

    @JsonProperty("commit")
    private CommitInfo mCommit;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class UserIcon {
        @JsonProperty("avatar_url")
        private String mAvatar;
    }

    @JsonProperty("author")
    private UserIcon mAuthor;

    @JsonProperty("committer")
    private UserIcon mCommitter;

    public String getSha() {
        return mSha;
    }

    public Committer getAuthor() {
        return mCommit != null ? mCommit.mAuthor : null;
    }

    public Committer getCommitter() {
        return mCommit != null ? mCommit.mCommitter : null;
    }

    public String getMessage() {
        return mCommit != null ? mCommit.mMessage : "";
    }

    public String getIcon() {
        if (mAuthor != null && !TextUtils.isEmpty(mAuthor.mAvatar)) {
            return mAuthor.mAvatar;
        } else if (mCommitter != null && !TextUtils.isEmpty(mCommitter.mAvatar)) {
            return mCommitter.mAvatar;
        } else {
            return "";
        }
    }
}
