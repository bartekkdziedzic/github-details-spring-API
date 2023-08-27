package com.example.rtask.mapper;

import com.example.rtask.model.GithubBranch;
import com.example.rtask.dto.GithubBranchDto;

public class BranchMapper {

    public static GithubBranchDto mapToDto(GithubBranch githubBranch) {

        var githubBranchDto = new GithubBranchDto();

        githubBranchDto.setName(githubBranch.getName());

        if (githubBranch.getCommit() != null)
            githubBranchDto.setLastCommitSha(githubBranch.getCommit().getSha());

        return githubBranchDto;
    }
}
