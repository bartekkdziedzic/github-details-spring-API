package com.example.rtask.dto;

import lombok.Builder;

@Builder
public record GithubBranchDto(String name, String lastCommitSha) {

}
