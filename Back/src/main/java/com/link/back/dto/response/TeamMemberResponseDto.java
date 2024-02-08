package com.link.back.dto.response;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.Collectors;

import com.link.back.entity.MemberStatus;
import com.link.back.entity.User;
import com.link.back.entity.UserTeam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamMemberResponseDto {

	private final Long userId;
	private final String name;
	private final Integer rating;
	private final String introduce;
	private final String userImageUrl;
	private final String userOriginImageName;
	private final List<SkillResponseDto> skillSets;

	public TeamMemberResponseDto(UserTeam userTeam) {
		User user = userTeam.getUser();
		this.userId = user.getUserId();
		this.name = user.getName();
		this.rating = user.getRating();
		this.introduce = user.getIntroduce();
		this.userImageUrl = user.getUserImage().getUserImageUrl();
		this.userOriginImageName = user.getUserImage().getUserOriginImageName();
		this.skillSets = user.getUserSkills().stream()
			.map(SkillResponseDto::new).collect(toList());
	}
}