package com.epam.dzmitry_slutski.githubapitest.network;

import android.util.Log;

import com.octo.android.robospice.retrofit.RetrofitJackson2SpiceService;

/**
 * GitHubService
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public class GitHubService extends RetrofitJackson2SpiceService {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("GitHubService", "onCreate");
        addRetrofitInterface(GitHubRetrofitInterface.class);
    }

    @Override
    protected String getServerUrl() {
        Log.d("GitHubService", "getServerUrl: " + GitHubApi.BASE_URL);
        return GitHubApi.BASE_URL;
    }
}