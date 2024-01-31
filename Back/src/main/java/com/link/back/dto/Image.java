package com.link.back.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Image {
	private final String imageName;
	private final String imageUrl;
	private final String originName;

	@Builder
	public Image(String imageName, String imageUrl, String originName) {
		this.imageName = imageName;
		this.imageUrl = imageUrl;
		this.originName = originName;
	}
}
