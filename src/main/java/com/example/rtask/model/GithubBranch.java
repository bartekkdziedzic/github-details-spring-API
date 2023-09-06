package com.example.rtask.model;

import lombok.Builder;

@Builder
public record GithubBranch(String name, GithubCommit commit) {

}
