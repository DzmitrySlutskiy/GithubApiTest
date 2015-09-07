package com.epam.githubapitest.network.robospice;

import android.util.Log;

import com.epam.githubapitest.model.GitHubCommit;
import com.epam.githubapitest.network.retrofit.GitHubRetrofitInterface;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * RepositoryRequest
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public class CommitRequest extends RetrofitSpiceRequest<GitHubCommit[], GitHubRetrofitInterface> {
    private String mUser;
    private String mRepo;

    public CommitRequest(String user, String repo) {
        super(GitHubCommit[].class, GitHubRetrofitInterface.class);
        Log.d("RepositoryRequest", "RepositoryRequest: " + user + " " + repo);
        mUser = user;
        mRepo = repo;
    }

    @Override
    public GitHubCommit[] loadDataFromNetwork() throws Exception {
        Log.d("RepositoryRequest", "loadDataFromNetwork ");
        return getService().getCommits(mUser, mRepo);
    }
}
