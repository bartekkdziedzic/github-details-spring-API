package com.example.rtask.repository;

import com.example.rtask.model.Owner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubRepository {

    private String name;
    private Owner owner;
    private boolean fork;


   // public String getUrl(){
   //     return "https://api.github.com/users/" + owner +" /repos";
  //  }

}
