package com.example.rtask.model;

import lombok.Builder;

@Builder
public record GithubRepository(String name, Owner owner, boolean fork) {

}
