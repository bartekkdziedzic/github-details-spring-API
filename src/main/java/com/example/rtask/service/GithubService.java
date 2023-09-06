package com.example.rtask.service;

import com.example.rtask.dto.GithubBranchDto;
import com.example.rtask.dto.GithubRepositoryDto;
import com.example.rtask.exception.GitServiceException;
import com.example.rtask.mapper.GithubMapper;
import com.example.rtask.model.GithubBranch;
import com.example.rtask.model.GithubRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GithubService {

    private final GithubHttpService githubHttpService;


    public List<GithubRepositoryDto> getUserRepos(String username) throws GitServiceException {

        List<GithubRepository> githubRepos = githubHttpService.getGithubRepoDto(username);

        List<GithubRepositoryDto> result = new ArrayList<>();

        for (GithubRepository githubRepo : githubRepos) {
            List<GithubBranch> repoBranches = githubHttpService.getGithubBranches(githubRepo.name(), username);
            List<GithubBranchDto> branchDtos = repoBranches.stream()
                    .map(GithubMapper::mapBranchToDto)
                    .toList();

            GithubRepositoryDto repositoryDto = GithubMapper.mapRepoToDto(githubRepo, branchDtos.stream().toList());
            result.add(repositoryDto);

        }
        return result;
    }
}
