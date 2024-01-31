package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import com.link.back.dto.request.UserImageRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserImage {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long userImageId;

	@Column(length = USER_IMAGE_NAME_LENGTH, nullable = false)
	private String userImageName;

	@Column(length = USER_IMAGE_URL, nullable = false)
	private String userImageUrl;

	@Column(length = USER_ORIGIN_IMAGE_NAME_LENGTH, nullable = false)
	private String userOriginImageName;

	@Builder
	public UserImage(UserImageRequest userImageRequest){
		this.userImageName = userImageRequest.getUserImageName();
		this.userImageUrl = userImageRequest.getUserImageUrl();
		this.userOriginImageName = userImageRequest.getUserOriginImageName();
	}
}
