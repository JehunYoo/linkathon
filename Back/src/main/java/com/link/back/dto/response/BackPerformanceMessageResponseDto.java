package com.link.back.dto.response;

import lombok.Builder;

@Builder
public record BackPerformanceMessageResponseDto(int addCount, int removeCount, int makeCount, int completeCount, int changeCount, int mergeCount, int refactorCount, int moveCount, int replaceCount, int etcCount) {
}
