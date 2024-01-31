package com.link.back.redmine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Status {

	private Long id;
	private String name;
	@JsonProperty("is_closed")
	private boolean isClosed;

}
