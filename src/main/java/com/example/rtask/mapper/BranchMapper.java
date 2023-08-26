package com.example.rtask.mapper;

import com.example.rtask.GithubBranch;
import com.example.rtask.dto.GithubBranchDto;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

import java.util.function.Function;


public class BranchMapper {

    public static GithubBranchDto mapToDto(GithubBranch githubBranch) {

        var githubBranchDto = new GithubBranchDto();

        githubBranchDto.setName(githubBranch.getName());

        if (githubBranch.getCommit() != null)
            githubBranchDto.setLastCommitSha(githubBranch.getCommit().getSha());

        return githubBranchDto;
    }
}
