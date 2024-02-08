package com.link.back.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.link.back.openfeign.ProjectContributionServiceClient;
import com.link.back.openfeign.dto.Contribution;
import com.link.back.openfeign.dto.GithubClientResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectContributionService{

	private final ProjectContributionServiceClient projectContributionServiceClient;

	public List<Contribution> getContributionsList(String repoOwner, String repoName) {
		List<GithubClientResponse> response = projectContributionServiceClient.getContributions(repoOwner,repoName);

		List<Contribution> contributionList = new ArrayList<>();
		for (GithubClientResponse res : response) {
			String userName = res.getAuthor().getLogin();
			int commits = res.getTotal();
			int totalAdditions = 0;
			int totalDeletions = 0;

			for (GithubClientResponse.WeekStats weekStats : res.getWeeks()) {
				totalAdditions += weekStats.getA();
				totalDeletions += weekStats.getD();
			}
			Contribution contribution = Contribution.builder().userName(userName).commits(commits).insertions(totalAdditions).deletions(totalDeletions).build();
			contributionList.add(contribution);
		}
		return contributionList;
	}
}

