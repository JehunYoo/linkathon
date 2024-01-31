package com.link.back.dto.response;

import static java.util.stream.Collectors.*;

import java.awt.*;
import java.util.List;

import com.link.back.entity.Field;
import com.link.back.entity.User;
import com.link.back.entity.UserImage;

import lombok.Getter;

@Getter
public class UserInfoResponsse {

	private String name;
	private List<SkillResponseDto> userSkills;
	private String referenceUrl;
	private Field field;
	private int career;
	private UserImage image;
	private boolean registered;

	public UserInfoResponsse(User user) {
		this.name = user.getName();
		this.userSkills = user.getUserSkills().stream()
			.map(SkillResponseDto::new).collect(toList());
		this.referenceUrl = user.getReferenceUrl();
		this.field = user.getField();
		this.career = user.getCareer();
		this.image = user.getUserImage();
		this.registered = user.isRegistered();
	}
}
