package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class TeamImage {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long teamImageId;

	@Column(length = USER_IMAGE_NAME_LENGTH, nullable = false)
	private String teamImageName;

	@Column(length = USER_IMAGE_URL, nullable = false)
	private String teamImageUrl;

	@Column(length = USER_ORIGIN_IMAGE_NAME_LENGTH, nullable = false)
	private String teamOriginImageName;

}
