package com.link.back.config;

import static lombok.AccessLevel.*;

import lombok.NoArgsConstructor;




@NoArgsConstructor(access = PRIVATE)
public final class AppConstant {
	public static final int PHONENUMBER_LENGTH = 11;
	public static final int USER_INTRODUCE = 100;

	// Team
	public static final int TEAM_NAME_LENGTH = 30;
	public static final int TEAM_DESC_LENGTH = 100;

	// Project
	public static final int PROJECT_NAME_LENGTH = 30;
	public static final int PROJECT_TOPIC_LENGTH = 30;
	public static final int PROJECT_DESC_LENGTH = 100;
	public static final int PROJECT_URL_LENGTH = 255;
	public static final int PROJECT_DEPLOY_LENGTH = 255;

	// ProjectImage
	public static final int PROJECT_IMAGE_NAME_LENGTH = 200;
	public static final int PROJECT_IMAGE_URL_LENGTH = 255;
	public static final int PROJECT_ORIGIN_IMAGE_NAME = 200;

	// Hackathon
	public static final int HACKATHON_NAME_LENGTH = 100;
	public static final int HACKATHON_IMAGE_NAME_LENGTH = 200;
	public static final int HACKATHON_IMAGE_URL_LENGTH = 255;
	public static final int HACKATHON_ORIGIN_IMAGE_NAME_LENGTH = 200;

	public static final int POST_FILE_NAME_LENGTH = 50;
	public static final int POST_FILE_URL_LENGTH = 100;
	public static final int POST_ORIGIN_FILE_URL_LENGTH = 50;
	public static final int POST_TITLE_LENGTH = 40;
	public static final int USER_IMAGE_NAME_LENGTH = 30;
	public static final int USER_IMAGE_URL = 100;
	public static final int USER_ORIGIN_IMAGE_NAME_LENGTH = 30;

	// Skill
	public static final int SKILL_NAME_LENGTH = 30;
	public static final int SKILL_IMAGE_URL_LENGTH = 255;
}
