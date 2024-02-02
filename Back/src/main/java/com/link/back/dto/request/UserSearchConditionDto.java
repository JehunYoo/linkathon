package com.link.back.dto.request;

import java.util.List;

import com.link.back.entity.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchConditionDto {

	private List<Long> skillIds;
	private Integer tier;
	private Integer career;
	private Boolean gender;
	private String field;

}
