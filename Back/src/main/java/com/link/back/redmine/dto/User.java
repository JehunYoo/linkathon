package com.link.back.redmine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	private Long id;
	private String login;

	// "id": 1,
	// 	"login": "admin",
	// 	"admin": true,
	// 	"firstname": "seongjin",
	// 	"lastname": "ma",
	// 	"mail": "making1104@gmail.com",
	// 	"created_on": "2024-01-29T11:50:36Z",
	// 	"updated_on": "2024-01-29T15:44:26Z",
	// 	"last_login_on": "2024-01-29T11:53:56Z",
	// 	"passwd_changed_on": "2024-01-29T11:54:44Z",
	// 	"twofa_scheme": null

}
