package com.link.back.openfeign.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Contribution {
	public String userName;
	public int commits;
	public int insertions;
	public int deletions;
	@Builder
	public Contribution(String userName, int commits, int insertions, int deletions) {
		this.userName = userName;
		this.commits = commits;
		this.insertions = insertions;
		this.deletions = deletions;
	}
}
