package com.link.back.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProjectImageDto(
	@NotNull String projectImgName,
	@NotNull String projectImgUrl,
	@NotNull String projectOriginImgName
) {
}
