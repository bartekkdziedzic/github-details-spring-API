package com.example.rtask;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubBranch {

    private String name;
    private GithubCommit commit;
}
