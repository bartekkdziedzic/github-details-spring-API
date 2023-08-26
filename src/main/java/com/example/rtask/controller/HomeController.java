package com.example.rtask.controller;

import com.example.rtask.dto.GithubRepositoryDto;
import com.example.rtask.repository.GithubRepository;
import com.example.rtask.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    private GithubService githubService;

    @Autowired
    public HomeController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/github/{username}")
    public List<GithubRepositoryDto> getData(@PathVariable("username") String username
                                             //   ,         @RequestHeader(value = "Accept") String header
    ) {
        return githubService.buildUlr(username);
    }

}
