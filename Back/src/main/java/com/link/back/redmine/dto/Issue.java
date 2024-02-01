package com.link.back.redmine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.link.back.redmine.dto.AssignedTo;
import com.link.back.redmine.dto.Author;
import com.link.back.redmine.dto.Priority;
import com.link.back.redmine.dto.Project;
import com.link.back.redmine.dto.Status;
import com.link.back.redmine.dto.Tracker;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

	private int id;
	private Project project;
	private Tracker tracker;
	private Status status;
	private Priority priority;
	private Author author;
	@JsonProperty("assigned_to")
	private AssignedTo assignedTo;
	private String subject;
	private String description;


}

// {
	// 	"issues":[
		// 	{
		// 	"id":3,
		// 	"project":{"id":1,"name":"Example name"},
		// 	"tracker":{"id":1,"name":"�� ���"},
		// 	"status":{"id":1,"name":"�ű�","is_closed":false},
		// 	"priority":{"id":1,"name":"�ſ����"},
		// 	"author":{"id":1,"name":"seongjin ma"},
		// 	"assigned_to":{"id":6,"name":"John Doe"},
		// 	"subject":"tesettt",
		// 	"description":"",
		// 	"start_date":"2024-01-29",
		// 	"due_date":null,
		// 	"done_ratio":0,
		// 	"is_private":false,
		// 	"estimated_hours":null,
		// 	"total_estimated_hours":null,
		// 	"spent_hours":0.0,
		// 	"total_spent_hours":0.0,
		// 	"created_on":"2024-01-29T16:08:04Z",
		// 	"updated_on":"2024-01-29T16:08:04Z",
		// 	"closed_on":null
		// 	}
	// 	]
// 	}