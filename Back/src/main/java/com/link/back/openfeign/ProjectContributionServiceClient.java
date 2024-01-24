package com.link.back.openfeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.link.back.openfeign.dto.GithubClientResponse;

import feign.Headers;

@FeignClient(name = "feign", url = "https://api.github.com")
public interface ProjectContributionServiceClient {
	@GetMapping("/repos/{owner}/{repo}/stats/contributors")
	@Headers("Accept: application/vnd.github+json")
	List<GithubClientResponse> getContributions(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}
