package com.example.rtask.service;

import com.example.rtask.exception.GitServiceException;
import com.example.rtask.model.GithubBranch;
import com.example.rtask.model.GithubRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class GithubHttpService {

    private WebClient.Builder webClient;

    public GithubHttpService(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl(baseUrl);
    }

    private final static String baseUrl = "https://api.github.com";


    public List<GithubRepository> getGithubRepoDto(String username) throws GitServiceException {

        final String userRepoEndpoint = "/users/{username}/repos";

        return webClient
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path(userRepoEndpoint).build(username))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> Mono.error(new GitServiceException("user " + username + " not found"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<GithubRepository>>(){})
                .block();
    }


    public List<GithubBranch> getGithubBranches(String repoName, String username) throws GitServiceException {

        final String branchesEndpoint = "/repos/{username}/{repo}/branches";

        return webClient
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path(branchesEndpoint).build(username, repoName))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> Mono.error(new GitServiceException("user " + username + " branches not found"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<GithubBranch>>(){})
                .block();
    }

}


