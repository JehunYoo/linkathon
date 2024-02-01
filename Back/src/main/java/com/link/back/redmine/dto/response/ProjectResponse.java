package com.link.back.redmine.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectResponse {

	private Long id;
	private String name;
	private String identifier;
	private String description;

}

// "id": 1,
// 	"name": "Example name",
// 	"identifier": "example_name",
// 	"description": "",
// 	"homepage": "",
// 	"status": 1,
// 	"is_public": true,
// 	"inherit_members": false,
// 	"created_on": "2024-01-29T13:38:12Z",
// 	"updated_on": "2024-01-29T15:12:11Z"