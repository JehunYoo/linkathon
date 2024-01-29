package com.link.back.controller.team;

import static org.springframework.http.HttpStatus.*;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.link.back.annotation.Login;
import com.link.back.dto.request.PermissionToRemoveMemberRequestDto;
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

	@PostMapping("/{teamId}/members/{userId}/permission/remove")
	@ResponseStatus(OK)
	public void postPermissionToRemoveMember(@PathVariable @Positive Long teamId, @PathVariable @Positive Long userId,
		@Login Long loginId, @RequestBody PermissionToRemoveMemberRequestDto requestDto) {

		teamService.permitToRemoveMember(teamId, userId, loginId, requestDto);
	}
}
