package com.example.rtask.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GithubBranch {

    private String name;
    private GithubCommit commit;
}
