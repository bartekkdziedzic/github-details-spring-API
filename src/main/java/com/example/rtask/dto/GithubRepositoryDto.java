package com.example.rtask.dto;

import com.example.rtask.model.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepositoryDto {

    private String name;
    private String ownerLogin;
    private GithubBranchDto[] branches;


}
