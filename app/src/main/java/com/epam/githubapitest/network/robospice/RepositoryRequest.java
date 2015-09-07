package com.epam.githubapitest.network.robospice;

import android.util.Log;

import com.epam.githubapitest.model.GitHubRepositories;
import com.epam.githubapitest.network.GitHubApi;
import com.epam.githubapitest.network.retrofit.GitHubRetrofitInterface;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * RepositoryRequest
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public class RepositoryRequest extends RetrofitSpiceRequest<GitHubRepositories, GitHubRetrofitInterface> {
    private String mSearchKey;

    public RepositoryRequest(String search) {
        super(GitHubRepositories.class, GitHubRetrofitInterface.class);
        Log.d("RepositoryRequest", "RepositoryRequest: " + search);
        mSearchKey = search;
    }

    @Override
    public GitHubRepositories loadDataFromNetwork() throws Exception {
        Log.d("RepositoryRequest", "loadDataFromNetwork: " + mSearchKey);
        return getService().getRepositories(mSearchKey, GitHubApi.KEY_SORT, GitHubApi.KEY_ORDER);
    }

    public void changeSearchKey(String searchKey) {
        mSearchKey = searchKey;
    }
}
