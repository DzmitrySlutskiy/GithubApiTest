package com.epam.githubapitest.network.retrofit;

import com.epam.githubapitest.model.GitHubCommit;
import com.epam.githubapitest.model.GitHubRepositories;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * GitHubOperation
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
public interface GitHubRetrofitInterface {

    @GET("/search/repositories")
    GitHubRepositories getRepositories(@Query("q") String searchKey,
                                     @Query("sort") String sort,
                                     @Query("order") String order);

    @GET("/repos/{user}/{repo}/commits")
    GitHubCommit[] getCommits(@Path("user") String user,
                            @Path("repo") String repo);
}
