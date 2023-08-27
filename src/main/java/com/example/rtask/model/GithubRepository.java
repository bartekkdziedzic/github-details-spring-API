package com.example.rtask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GithubRepository {

    private String name;
    private Owner owner;
    private boolean fork;

}
