package com.example.rtask.dto;

import lombok.Builder;

@Builder
public record GithubRepositoryDto(String name, String ownerLogin, GithubBranchDto[] branches) {

}
