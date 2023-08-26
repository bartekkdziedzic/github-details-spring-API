package com.example.rtask.service;

import com.example.rtask.GithubBranch;
import com.example.rtask.GithubCommit;
import com.example.rtask.dto.GithubBranchDto;
import com.example.rtask.dto.GithubRepositoryDto;
import com.example.rtask.mapper.BranchMapper;
import com.example.rtask.repository.GithubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {


    public List<GithubRepositoryDto> buildUlr(String username) {
        RestTemplate restTemplate = new RestTemplate();

        String base = "https://api.github.com/users/{username}/repos";
        ResponseEntity<GithubRepository[]> response = restTemplate.exchange(base, HttpMethod.GET, null, GithubRepository[].class, username);
        GithubRepository[] repositories = response.getBody();
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
