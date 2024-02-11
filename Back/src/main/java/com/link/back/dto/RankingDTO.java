package com.link.back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankingDTO {
	private long userId;
	private String name;
	private String introduce;
	private int rating;
	private String referenceUrl;
	private String userImageUrl;
}
