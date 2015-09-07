package com.epam.githubapitest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * GitHubErrorResponse
 * Version info
 * 04.02.2015
 * Created by Dzmitry_Slutski.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubErrorResponse {
    /*{
        "message": "Validation Failed",
            "errors": [
        {
            "resource": "Search",
                "field": "q",
                "code": "missing"
        }
        ],
        "documentation_url": "https://developer.github.com/v3/search"
    }*/
    public String message;

    public class Error {
        public String resource;
        public String field;
        public String code;
    }

    public Error[] errors;

    public String documentation_url;
}
