package com.link.back.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PermissionToRemoveMemberRequestDto {

	@NotNull
	@Positive
	private Long memberId;

	@NotNull
	private String uuid;
}
