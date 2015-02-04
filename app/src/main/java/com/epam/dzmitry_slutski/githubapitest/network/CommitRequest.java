package com.epam.dzmitry_slutski.githubapitest.network;

import android.util.Log;

import com.epam.dzmitry_slutski.githubapitest.model.Commit;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * RepositoryRequest
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public class CommitRequest extends RetrofitSpiceRequest<Commit[], GitHubRetrofitInterface> {
    private String mUser;
    private String mRepo;

    public CommitRequest(String user, String repo) {
        super(Commit[].class, GitHubRetrofitInterface.class);
        Log.d("RepositoryRequest", "RepositoryRequest: " + user + " " + repo);
        mUser = user;
        mRepo = repo;
    }

    @Override
    public Commit[] loadDataFromNetwork() throws Exception {
        Log.d("RepositoryRequest", "loadDataFromNetwork ");
        return getService().getCommits(mUser, mRepo);
    }
}
