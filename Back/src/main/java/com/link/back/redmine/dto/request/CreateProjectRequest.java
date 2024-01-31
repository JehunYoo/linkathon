package com.link.back.redmine.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProjectRequest implements Request {

	private String name;
	private String identifier;

	@Builder
	public CreateProjectRequest(String name, String identifier) {
		this.name = name;
		this.identifier = identifier;
	}


}
