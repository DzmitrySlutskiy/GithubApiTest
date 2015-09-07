package com.epam.githubapitest.ui.activity;

import android.support.v7.app.ActionBarActivity;

import com.epam.githubapitest.network.GitHubService;
import com.epam.githubapitest.network.GitHubSpiceManager;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.SpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * BaseActivity
 * Version info
 * 05.02.2015
 * Created by Dzmitry_Slutski.
 */
public abstract class BaseActivity extends ActionBarActivity {
    public static final String CACHE_KEY = "github_cache_key";
    private SpiceManager mSpiceManager = new GitHubSpiceManager(GitHubService.class);


    protected abstract SpiceRequest getRequest();

    protected abstract RequestListener getListener();

    @Override
    protected void onStop() {
        mSpiceManager.shouldStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        mSpiceManager.start(this);
        super.onStart();
    }

    protected void executeRequest() {
        mSpiceManager.execute(getRequest(), CACHE_KEY, DurationInMillis.ONE_SECOND, getListener());
    }
}
