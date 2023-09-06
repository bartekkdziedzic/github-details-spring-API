package com.example.rtask.controller;

import com.example.rtask.dto.GithubRepositoryDto;
import com.example.rtask.exception.GitServiceException;
import com.example.rtask.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GithubController implements ErrorController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping(value = "/github/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<GithubRepositoryDto> getData(@PathVariable("username") String username) throws GitServiceException {

        return githubService.getUserRepos(username);
    }


}
