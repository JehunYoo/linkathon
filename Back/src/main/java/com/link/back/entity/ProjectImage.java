package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class ProjectImage {

	@Id
	@GeneratedValue
	private Long projectImageId;

	@Column(length = PROJECT_IMAGE_NAME_LENGTH, nullable = false)
	private String projectImageName;

	@Column(length = PROJECT_IMAGE_URL_LENGTH, nullable = false)
	private String projectImageUrl;

	@Column(length = PROJECT_ORIGIN_IMAGE_NAME, nullable = false)
	private String projectOriginImageName;
}
