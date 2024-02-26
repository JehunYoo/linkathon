package com.link.back.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.link.back.entity.Field;
import com.link.back.entity.UserImage;
import com.link.back.entity.UserSkill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserUpdateInfoRequest {
	private String password;
	private String phoneNumber;
	private String name;
	private String introduce;
	private LocalDate birth;
	private boolean gender;
	private List<UserSkill> userSkills;
	private String referenceUrl;
	private Field field;
	private int career;
	private UserImage userImage;
	private boolean registered;
}
