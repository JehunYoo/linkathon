package com.link.back.redmine.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest implements Request {

	private String login;
	private String password;
	@JsonProperty("firstname")
	private String firstName;
	@JsonProperty("lastname")
	private String lastName;
	private String mail;

	public CreateUserRequest(String login, String password, String firstName, String lastName, String mail) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
	}
}
