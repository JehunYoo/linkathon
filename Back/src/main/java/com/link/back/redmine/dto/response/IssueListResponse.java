package com.link.back.redmine.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.link.back.redmine.dto.Issue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueListResponse implements Response {

	private List<Issue> issues;
	@JsonProperty("total_count")
	private int totalCnt;
	private int offset;
	private int limit;

}
