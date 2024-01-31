package com.link.back.redmine.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.link.back.redmine.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListResponse implements Response {

	private List<User> users;

}

// {
// 	"users": [
// 	{
// 	"id": 1,
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
// 	},
// 	{
// 	"id": 5,
// 	"login": "newuser",
// 	"admin": false,
// 	"firstname": "ㅇ",
// 	"lastname": "ㅇ",
// 	"mail": "sd@gmail.com",
// 	"created_on": "2024-01-29T15:46:03Z",
// 	"updated_on": "2024-01-29T15:46:03Z",
// 	"last_login_on": null,
// 	"passwd_changed_on": "2024-01-29T15:46:03Z",
// 	"twofa_scheme": null
// 	},
// 	{
// 	"id": 6,
// 	"login": "newuser1",
// 	"admin": false,
// 	"firstname": "John",
// 	"lastname": "Doe",
// 	"mail": "john.doe@example.com",
// 	"created_on": "2024-01-29T15:53:27Z",
// 	"updated_on": "2024-01-29T15:53:27Z",
// 	"last_login_on": null,
// 	"passwd_changed_on": "2024-01-29T15:53:27Z",
// 	"twofa_scheme": null
// 	},
// 	{
// 	"id": 7,
// 	"login": "userTest",
// 	"admin": false,
// 	"firstname": "user",
// 	"lastname": "test",
// 	"mail": "userTest123@gmail.com",
// 	"created_on": "2024-01-30T05:23:00Z",
// 	"updated_on": "2024-01-30T05:23:00Z",
// 	"last_login_on": null,
// 	"passwd_changed_on": "2024-01-30T05:23:00Z",
// 	"twofa_scheme": null
// 	}
// 	],
// 	"total_count": 4,
// 	"offset": 0,
// 	"limit": 25
// 	}