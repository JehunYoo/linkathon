package com.link.back.controller.team;

import static org.springframework.http.HttpStatus.*;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.link.back.annotation.Login;
import com.link.back.dto.response.SuggestedCandidatesResponseDto;
import com.link.back.service.TeamBuildingSuggestionService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teams")
@Validated
@RequiredArgsConstructor
public class TeamBuildingSuggestionController {

	private final TeamBuildingSuggestionService teamBuildingSuggestionService;

	@PostMapping("/{teamId}/members/{userId}/suggest")
	@ResponseStatus(OK)
	public void postSuggestionByTeam(@PathVariable @Positive Long teamId, @PathVariable @Positive Long userId) {
		teamBuildingSuggestionService.suggestTeamToUser(teamId, userId);
	}

	@DeleteMapping("/{teamId}/members/{userId}/suggest")
	@ResponseStatus(NO_CONTENT)
	public void deleteSuggestionByTeam(@PathVariable @Positive Long teamId, @PathVariable @Positive Long userId) {
		teamBuildingSuggestionService.cancelSuggestion(teamId, userId);
	}

	@PostMapping("/{teamId}/members/suggest")
	@ResponseStatus(OK)
	public void postSuggestionByUser(@PathVariable @Positive Long teamId, @Login @Positive Long userId) {
		teamBuildingSuggestionService.acceptSuggestion(teamId, userId);
	}

	@DeleteMapping("/{teamId}/members/suggest")
	@ResponseStatus(NO_CONTENT)
	public void deleteSuggestionByUser(@PathVariable @Positive Long teamId, @Login @Positive Long userId) {
		teamBuildingSuggestionService.refuseSuggestion(teamId, userId);
	}

	@GetMapping("/{teamId}/suggest")
	@ResponseStatus(OK)
	public SuggestedCandidatesResponseDto getSuggestionListOfTeam(@PathVariable @Positive Long teamId) { // todo: create dto
		return teamBuildingSuggestionService.getSuggestionListOfTeam(teamId);
	}

	@GetMapping("/suggest")
	@ResponseStatus(OK)
	public void getSuggestionListOfUser(@Login @Positive Long userId) { // todo: create dto
		teamBuildingSuggestionService.getSuggestionListOfUser(userId);
	}
}
