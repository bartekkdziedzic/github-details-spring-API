package com.example.rtask.mapper;

import com.example.rtask.dto.GithubBranchDto;
import com.example.rtask.dto.GithubRepositoryDto;
import com.example.rtask.model.GithubBranch;
import com.example.rtask.model.GithubCommit;
import com.example.rtask.model.GithubRepository;
import com.example.rtask.model.Owner;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class GithubMapperTest {

    @Test
    void mapBranchToDto() {
        GithubBranch testBranch = GithubBranch
                .builder()
                .name("TEST_BRANCH")
                .commit(new GithubCommit("TEST_SHA"))
                .build();

        GithubBranchDto result = GithubMapper.mapBranchToDto(testBranch);


        GithubBranchDto expectedResult = GithubBranchDto
                .builder()
                .name("TEST_BRANCH")
                .lastCommitSha("TEST_SHA")
                .build();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void mapBranchToDtoThrowsWhenNullGiven() {

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> GithubMapper.mapBranchToDto(null));
    }


    @Test
    void mapRepoToDto() {

        GithubRepository testRepository = GithubRepository
                .builder()
                .name("TEST_REPO")
                .owner(new Owner("TEST_OWNER"))
                .fork(false)
                .build();

        GithubBranchDto repoTestBranchDto = GithubBranchDto
                .builder()
                .name("TEST_BRANCH")
                .lastCommitSha("TEST_SHA")
                .build();

        GithubRepositoryDto result = GithubMapper.mapRepoToDto(testRepository,
                List.of(repoTestBranchDto));

        GithubRepositoryDto expectedResult = GithubRepositoryDto
                .builder()
                .name("TEST_REPO")
                .ownerLogin("TEST_OWNER")
                .branches(List.of(repoTestBranchDto))
                .build();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void mapRepoToDtoThrowsWhenNullGiven() {

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> GithubMapper.mapRepoToDto(null, null));
    }
}