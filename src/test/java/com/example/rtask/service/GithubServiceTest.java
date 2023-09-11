package com.example.rtask.service;

import com.example.rtask.dto.GithubBranchDto;
import com.example.rtask.dto.GithubRepositoryDto;
import com.example.rtask.exception.GitServiceException;
import com.example.rtask.model.GithubBranch;
import com.example.rtask.model.GithubCommit;
import com.example.rtask.model.GithubRepository;
import com.example.rtask.model.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class GithubServiceTest {

    @Autowired
    private GithubService githubService;

    @MockBean
    private GithubHttpService githubHttpService;


    @Test
    void getUserRepos() throws GitServiceException {

        GithubBranch githubBranch = GithubBranch
                .builder()
                .name("TEST_NAME")
                .commit(new GithubCommit("TEST_SHA"))
                .build();


        when(githubHttpService.getGithubRepoDto(anyString()))
                .thenReturn(List.of(new GithubRepository("repo1", new Owner("TEST_USER"), false)));

        when(githubHttpService.getGithubBranches(anyString(), anyString()))
                .thenReturn(List.of(githubBranch));


        List<GithubRepositoryDto> result = githubService.getUserRepos("TEST_USER");

        GithubBranchDto branchDto = GithubBranchDto
                .builder()
                .name("TEST_NAME")
                .lastCommitSha("TEST_SHA")
                .build();


        GithubRepositoryDto expectedDto = GithubRepositoryDto
                .builder()
                .name("repo1")
                .ownerLogin("TEST_USER")
                .branches(List.of(branchDto))

                .build();

        assertThat(result).isEqualTo(List.of(expectedDto));
    }
}