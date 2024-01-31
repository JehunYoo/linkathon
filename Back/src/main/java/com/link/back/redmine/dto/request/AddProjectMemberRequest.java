package com.link.back.redmine.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProjectMemberRequest implements Request {
	@JsonProperty("user_id")
	private Long userId;
	@JsonProperty("role_ids")
	private List<Integer> roleIds;
}
