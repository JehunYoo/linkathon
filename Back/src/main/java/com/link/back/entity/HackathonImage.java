package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class HackathonImage {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long hackathonImageId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hackathon_id", nullable = false)
	private Hackathon hackathon;

	@Column(nullable = false, length = HACKATHON_IMAGE_NAME_LENGTH)
	private String hackathonImageName;

	@Column(nullable = false, length = HACKATHON_IMAGE_URL_LENGTH)
	private String hackathonImageUrl;

	@Column(nullable = false, length = HACKATHON_ORIGIN_IMAGE_NAME_LENGTH)
	private String hackathonOriginImageName;
}
