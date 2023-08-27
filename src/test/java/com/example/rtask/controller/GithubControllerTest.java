package com.example.rtask.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.rtask.dto.GithubBranchDto;
import com.example.rtask.dto.GithubRepositoryDto;
import com.example.rtask.exception.GitServiceException;
import com.example.rtask.service.GithubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(GithubController.class)
class GithubControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GithubService githubService;

    @Test
    public void endpointShouldReturnListOfGithubRepos() throws Exception {

        GithubBranchDto mainBranch = GithubBranchDto.builder().name("main").lastCommitSha("ABCD").build();
        GithubBranchDto testBranch = GithubBranchDto.builder().name("test").lastCommitSha("QWERTY").build();

        GithubRepositoryDto expectedResponse = GithubRepositoryDto.builder()
                .name("TEST_USER")
                .ownerLogin("TEST_LOGIN")
                .branches(List.of(mainBranch, testBranch).toArray(GithubBranchDto[]::new))
                .build();

        String expectedResponseAsString = new ObjectMapper().writeValueAsString(List.of(expectedResponse));
        when(githubService.getUserRepos(anyString())).thenReturn(List.of(expectedResponse));

        this.mockMvc.perform(get("/api/github/TEST_USER"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseAsString));
    }

    @Test
    public void endpointShouldReturnNotFound() throws Exception {

        GitServiceException exception = new GitServiceException("test not found");
        when(githubService.getUserRepos(anyString())).thenThrow(exception);


        String expected = new JSONObject()
                .put("status","NOT_FOUND")
                .put("message","test not found")
                .toString();

        this.mockMvc.perform(get("/api/github/TEST_USER"))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expected));
    }

    @Test
    public void endpointShouldReturnJsonOnly() throws Exception {

        String expected = new JSONObject()
                .put("status","NOT_ACCEPTABLE")
                .put("message","No acceptable representation")
                .toString();

        this.mockMvc.perform(get("/api/github/TEST_USER").accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().json(expected));
    }

}


