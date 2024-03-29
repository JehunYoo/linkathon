package com.link.back.dto.response;

import static java.util.stream.Collectors.*;

import java.util.List;

import com.link.back.entity.Role;
import com.link.back.entity.User;
import com.link.back.entity.UserImage;
import com.link.back.entity.UserSkill;
import com.link.back.entity.UserTeam;

import lombok.Getter;

@Getter
public class MemberResponseDto {

	private final String name;

	private final Role role;

	private final Integer rating;

	private final String introduce;

	private final String userImageUrl;

	private final String userOriginImageName;

	private final List<SkillResponseDto> skillSets;

	public MemberResponseDto(UserTeam userTeam) {
		User user = userTeam.getUser();
		UserImage userImage = user.getUserImage();
		List<UserSkill> userSkills = user.getUserSkills();

		this.name = user.getName();
		this.role = userTeam.getRole();
		this.rating = user.getRating();
		this.introduce = user.getIntroduce();
		this.userImageUrl = userImage.getUserImageUrl();
		this.userOriginImageName = userImage.getUserOriginImageName();
		this.skillSets = userSkills.stream()
			.map(SkillResponseDto::new)
			.collect(toList());
	}
}
