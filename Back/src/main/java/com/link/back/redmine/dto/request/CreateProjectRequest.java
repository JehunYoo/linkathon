package com.link.back.redmine.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProjectRequest implements Request {

	private String name;
	private String identifier;

}
