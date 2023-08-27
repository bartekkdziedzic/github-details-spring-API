package com.example.rtask.service;

import com.example.rtask.exception.GitServiceException;
import com.example.rtask.model.GithubBranch;
import com.example.rtask.model.GithubRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@AllArgsConstructor
public class GithubHttpService {

    private final RestTemplate restTemplate;
    private final static String baseUrl = "https://api.github.com";


    public GithubRepository[] getGithubRepoDto(String username) throws GitServiceException {

        final String userRepoEndpoint = "/users/{username}/repos";

        try {
            GithubRepository[] repositories = restTemplate.getForObject(baseUrl + userRepoEndpoint, GithubRepository[].class, username);
            Objects.requireNonNull(repositories);
            return repositories;
        } catch (HttpClientErrorException | NullPointerException e) {
            throw new GitServiceException("custom");
        }
    }


    public GithubBranch[] getGithubBranches(String repoName, String username) throws GitServiceException {

        final String branchesEndpoint = "/repos/{username}/{repo}/branches";

        try {
            GithubBranch[] branches = restTemplate.getForObject(baseUrl + branchesEndpoint, GithubBranch[].class, username, repoName);
            Objects.requireNonNull(branches);
            return branches;
        } catch (HttpClientErrorException | NullPointerException e) {
            throw new GitServiceException("custom");
        }
    }

}


