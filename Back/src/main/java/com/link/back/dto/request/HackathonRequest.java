package com.link.back.dto.request;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
public record HackathonRequest(String hackathonName, LocalDate teamDeadlineDate, LocalDate startDate, LocalDate endDate, int maxPoint) {
}
