package com.link.back.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IdsResponseDto {
	private List<Long> ids;
	private List<String> names;
}
