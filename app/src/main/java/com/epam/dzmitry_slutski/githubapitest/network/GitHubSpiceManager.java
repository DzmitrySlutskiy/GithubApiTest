package com.epam.dzmitry_slutski.githubapitest.network;

import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.SpiceService;

import roboguice.util.temp.Ln;

/**
 * GitHubSpiceManager
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public class GitHubSpiceManager extends SpiceManager {
    public GitHubSpiceManager(Class<? extends SpiceService> spiceServiceClass) {
        super(spiceServiceClass);
        Log.d("GitHubSpiceManager", "GitHubSpiceManager");
        Ln.getConfig().setLoggingLevel(Log.VERBOSE);
    }
}