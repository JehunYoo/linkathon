package com.link.back.dto.request;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import com.link.back.entity.Field;
import com.link.back.entity.UserImage;
import com.link.back.entity.UserSkill;

import lombok.Getter;

@Getter
public class AdditionalUserInfoRequest {

	private String email;

	private List<UserSkill> userSkills;

	private String referenceUrl;

	private Field field;

	private UserImage userImage;

	private String introduce;

	// 얘 트루면 registeredDate 현재시간으로 넣어줌
	private boolean registered;
}
