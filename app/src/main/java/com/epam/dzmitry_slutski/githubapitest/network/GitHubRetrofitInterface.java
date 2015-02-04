package com.epam.dzmitry_slutski.githubapitest.network;

import com.epam.dzmitry_slutski.githubapitest.model.Commit;
import com.epam.dzmitry_slutski.githubapitest.model.GitHubRepository;

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
    GitHubRepository getRepositories(@Query("q") String searchKey,
                                     @Query("sort") String sort,
                                     @Query("order") String order);

    @GET("/repos/{user}/{repo}/commits")
    Commit[] getCommits(@Path("user") String user,
                            @Path("repo") String repo);
}
