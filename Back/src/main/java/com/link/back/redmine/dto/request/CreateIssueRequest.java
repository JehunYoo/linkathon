package com.link.back.redmine.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIssueRequest implements Request{

	@JsonProperty("project_id")
	private Long projectId;
	@JsonProperty("tracker_id")
	private Long trackerId;
	@JsonProperty("status_id")
	private Long statusId;
	@JsonProperty("priority_id")
	private Long priorityId;
	@JsonProperty("assigned_to_id")
	private Long assignedToId;
	private String subject;

	public CreateIssueRequest(String subject) {
		this.trackerId = 1L;
		this.statusId = 1L;
		this.priorityId = 1L;
		this.subject = subject;
	}
}
