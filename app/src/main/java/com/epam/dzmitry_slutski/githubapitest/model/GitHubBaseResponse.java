package com.epam.dzmitry_slutski.githubapitest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * GitHubBaseResponse
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubBaseResponse<T> extends GitHubErrorResponse {

    @JsonProperty("total_count")
    private int mTotalCount;

    @JsonProperty("incomplete_results")
    private boolean mIncompleteResults;

    @JsonProperty("items")
    private List<T> mItems;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("mTotalCount: " + mTotalCount + "\n")
                .append("incomplete_results: " + mIncompleteResults + "\n");
        if (mItems != null && mItems.size() > 0) {
            for (T t : mItems) {
                builder.append("item: " + t + "\n");
            }
        } else {
            builder.append("items size: 0");
        }
        return builder.toString();
    }

    public int getTotalCount() {
        return mTotalCount;
    }

    public boolean ismIncompleteResults() {
        return mIncompleteResults;
    }

    public List<T> getItems() {
        return mItems;
    }
}
