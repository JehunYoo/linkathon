package com.link.back.controller.team;

import static org.springframework.http.HttpStatus.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.link.back.annotation.Login;
import com.link.back.dto.request.PermissionToRemoveMemberRequestDto;
import com.link.back.dto.request.TeamSearchConditionDto;
import com.link.back.dto.response.TeamRecruitResponseDto;
import com.link.back.service.TeamService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teams")
@Validated
@RequiredArgsConstructor
public class TeamController {

	private final TeamService teamService;

	@DeleteMapping("/{teamId}/members/{userId}")
	@ResponseStatus(NO_CONTENT)
	public void deleteMember(@PathVariable @Positive Long teamId, @PathVariable @Positive Long userId,
		@Login Long loginId) {
		teamService.requestToRemoveMember(teamId, userId, loginId);
	}

	@GetMapping("/{teamId}/members/{userId}/permission/remove")
	@ResponseStatus(OK)
	public void postPermissionToRemoveMember(@PathVariable @Positive Long teamId, @PathVariable @Positive Long userId,
		@ModelAttribute PermissionToRemoveMemberRequestDto requestDto) {

		teamService.permitToRemoveMember(teamId, userId, requestDto.getMemberId(), requestDto.getCode());
	}

	@GetMapping("/hackathon/{hackathonId}")
	@ResponseStatus(OK)
	public Page<TeamRecruitResponseDto> getTeamByHackathon(
		@PathVariable Long hackathonId,
		TeamSearchConditionDto teamSearchConditionDto,
		@RequestParam int page,
		@RequestParam int size) {
		Pageable pageable = PageRequest.of(page-1, size);
		return teamService.findTeamByHackathonAndTeamSearchCond(hackathonId, teamSearchConditionDto, pageable);
	}
}
