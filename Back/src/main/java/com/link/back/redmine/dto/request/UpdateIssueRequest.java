package com.link.back.redmine.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateIssueRequest implements Request {

	@JsonProperty("status_id")
	private Long statusId;
	@JsonProperty("assigned_to_id")
	private Long assignedToId;
	private String subject;

}
