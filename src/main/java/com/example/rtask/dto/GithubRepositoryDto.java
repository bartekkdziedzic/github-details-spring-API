package com.example.rtask.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record GithubRepositoryDto(String name, String ownerLogin, List<GithubBranchDto> branches) {

}
