package com.example.rtask.mapper;

import com.example.rtask.dto.GithubRepositoryDto;
import com.example.rtask.model.GithubBranch;
import com.example.rtask.dto.GithubBranchDto;
import com.example.rtask.model.GithubRepository;

import java.util.Objects;

public class GithubMapper {

    public static GithubBranchDto mapBranchToDto(GithubBranch githubBranch) {

        Objects.requireNonNull(githubBranch);

        return GithubBranchDto.builder()
                .name(githubBranch.name())
                .lastCommitSha(githubBranch.commit().sha())
                .build();
    }

    public static GithubRepositoryDto mapRepoToDto(GithubRepository githubRepository, GithubBranchDto[] branchDtos) {

        Objects.requireNonNull(githubRepository);

        return GithubRepositoryDto.builder()
                .name(githubRepository.name())
                .ownerLogin(githubRepository.owner().login())
                .branches(branchDtos)
                .build();
    }

}
