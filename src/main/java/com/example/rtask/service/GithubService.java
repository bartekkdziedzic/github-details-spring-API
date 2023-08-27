package com.example.rtask.service;

import com.example.rtask.exception.GitServiceException;
import com.example.rtask.model.GithubBranch;
import com.example.rtask.dto.GithubBranchDto;
import com.example.rtask.dto.GithubRepositoryDto;
import com.example.rtask.mapper.BranchMapper;
import com.example.rtask.model.HttpModel;
import com.example.rtask.repository.GithubRepository;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GithubService {


    public List<GithubRepositoryDto> buildUlr(String username) throws GitServiceException {
        RestTemplate restTemplate = new RestTemplate();

        String base = "https://api.github.com/users/{username}/repos";

        GithubRepository[] repositories;
        ResponseEntity<GithubRepository[]> response ;

        try {
        response = restTemplate.exchange(base, HttpMethod.GET, null, GithubRepository[].class, username);
        }
        catch (HttpClientErrorException.NotFound  e){
             throw new GitServiceException("custom");
        }
         repositories = response.getBody();

        return githubReposDetailListing(repositories);
    }

    public List<GithubRepositoryDto> githubReposDetailListing(GithubRepository[] repositories) {
        RestTemplate restTemplate = new RestTemplate();

        String base = "https://api.github.com/repos/{username}/{repo}/branches";
        List<GithubRepositoryDto> responseRepos = new ArrayList<>();

        for (GithubRepository repo : repositories) {

            if (!repo.isFork()) {
                GithubRepositoryDto githubRepositoryDto = new GithubRepositoryDto();
                githubRepositoryDto.setName(repo.getName());
                githubRepositoryDto.setOwnerLogin(repo.getOwner().getLogin());

                ResponseEntity<GithubBranch[]> branchResponse = restTemplate.exchange(base, HttpMethod.GET, null
                        , GithubBranch[].class, githubRepositoryDto.getOwnerLogin(), repo.getName());

                GithubBranch[] branches = branchResponse.getBody();

                githubRepositoryDto.setBranches(Arrays.stream(branches)
                        .map(BranchMapper::mapToDto).toList().toArray(new GithubBranchDto[0]));

                responseRepos.add(githubRepositoryDto);
            }
        }
        return responseRepos;
    }
}
