package com.link.back.dto.response;

import com.link.back.entity.UserImage;

import lombok.Getter;

@Getter
public class UserImageResponseDto {

	private final String userImageName;
	private final String userImageUrl;
	private final String userOriginImageName;

	public UserImageResponseDto(UserImage userImage){
		this.userImageName = userImage.getUserImageName();
		this.userImageUrl = userImage.getUserImageUrl();
		this.userOriginImageName = userImage.getUserOriginImageName();
	}
}
