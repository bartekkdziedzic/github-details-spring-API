package com.example.rtask.dto;

import com.example.rtask.GithubCommit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class GithubBranchDto {
    private String name;
    private String lastCommitSha;
}
