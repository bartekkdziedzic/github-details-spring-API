package com.example.rtask.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubRepositoryDto {

    private String name;
    private String ownerLogin;
    private GithubBranchDto[] branches;
}
