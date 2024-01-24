package com.link.back.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.link.back.openfeign.dto.Contribution;
import com.link.back.openfeign.dto.GithubClientResponse;
import com.link.back.service.ProjectContributionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {
	private final ProjectContributionService projectContributionService;
	@GetMapping("/contributions/{owner}/{repo}")
	public ResponseEntity<List<Contribution>> getContributions(@PathVariable String owner, @PathVariable String repo) {
		List<Contribution> contributions = projectContributionService.getContributionsList(owner,repo);
		return new ResponseEntity<>(contributions, HttpStatus.OK);
	}
}
