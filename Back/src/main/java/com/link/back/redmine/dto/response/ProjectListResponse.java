package com.link.back.redmine.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectListResponse implements Response {

	private List<ProjectResponse> projects;

}

// {
// 	"projects": [
// 	{
// 	"id": 1,
// 	"name": "Example name",
// 	"identifier": "example_name",
// 	"description": "",
// 	"homepage": "",
// 	"status": 1,
// 	"is_public": true,
// 	"inherit_members": false,
// 	"created_on": "2024-01-29T13:38:12Z",
// 	"updated_on": "2024-01-29T15:12:11Z"
// 	},
// 	{
// 	"id": 4,
// 	"name": "e",
// 	"identifier": "ss",
// 	"description": null,
// 	"homepage": "",
// 	"status": 1,
// 	"is_public": true,
// 	"inherit_members": false,
// 	"created_on": "2024-01-30T04:32:00Z",
// 	"updated_on": "2024-01-30T04:32:00Z"
// 	},
// 	{
// 	"id": 5,
// 	"name": "ee",
// 	"identifier": "sss",
// 	"description": null,
// 	"homepage": "",
// 	"status": 1,
// 	"is_public": true,
// 	"inherit_members": false,
// 	"created_on": "2024-01-30T04:58:16Z",
// 	"updated_on": "2024-01-30T04:58:16Z"
// 	},
// 	{
// 	"id": 6,
// 	"name": "eee",
// 	"identifier": "ssss",
// 	"description": null,
// 	"homepage": "",
// 	"status": 1,
// 	"is_public": true,
// 	"inherit_members": false,
// 	"created_on": "2024-01-30T05:00:04Z",
// 	"updated_on": "2024-01-30T05:00:04Z"
// 	},
// 	{
// 	"id": 2,
// 	"name": "test",
// 	"identifier": "test",
// 	"description": "test",
// 	"homepage": "",
// 	"status": 1,
// 	"is_public": true,
// 	"inherit_members": false,
// 	"created_on": "2024-01-29T15:14:19Z",
// 	"updated_on": "2024-01-29T15:14:19Z"
// 	},
// 	{
// 	"id": 3,
// 	"name": "test2",
// 	"identifier": "test2",
// 	"description": "te",
// 	"homepage": "",
// 	"status": 1,
// 	"is_public": true,
// 	"inherit_members": false,
// 	"created_on": "2024-01-29T15:37:34Z",
// 	"updated_on": "2024-01-29T15:37:34Z"
// 	}
// 	],
// 	"total_count": 6,
// 	"offset": 0,
// 	"limit": 25
// 	}
