package com.link.back.dto.response;

import lombok.Builder;

@Builder
public record LighthouseResponseDTO(
	String url,
	String report) {
}
