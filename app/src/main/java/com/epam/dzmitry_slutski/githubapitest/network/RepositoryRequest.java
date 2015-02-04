package com.epam.dzmitry_slutski.githubapitest.network;

import android.util.Log;

import com.epam.dzmitry_slutski.githubapitest.model.GitHubRepository;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * RepositoryRequest
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public class RepositoryRequest extends RetrofitSpiceRequest<GitHubRepository, GitHubRetrofitInterface> {
    private String mSearchKey;

    public RepositoryRequest(String search) {
        super(GitHubRepository.class, GitHubRetrofitInterface.class);
        Log.d("RepositoryRequest", "RepositoryRequest: " + search);
        mSearchKey = search;
    }

    @Override
    public GitHubRepository loadDataFromNetwork() throws Exception {
        Log.d("RepositoryRequest", "loadDataFromNetwork: " + mSearchKey);
        return getService().getRepositories(mSearchKey, GitHubApi.KEY_SORT, GitHubApi.KEY_ORDER);
    }

    public void changeSearchKey(String searchKey) {
        mSearchKey = searchKey;
    }
}
