package com.link.back.dto.request;

import lombok.Builder;

@Builder
public record RefreshTokenRequest(String refreshToken) {
}
