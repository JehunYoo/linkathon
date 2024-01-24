package com.link.back.openfeign.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class GithubClientResponse {

	private int total;
	private List<WeekStats> weeks;
	private Author author;

	@Getter
	public static class Author {
		private String login;
	}

	@Getter
	public static class WeekStats {
		private long w; // Unix timestamp
		private int a;  // Additions
		private int d;  // Deletions
		private int c;  // Commits
	}
}


