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
import com.link.back.dto.response.CandidatesResponseDto;
import com.link.back.dto.response.MembersResponseDto;
import com.link.back.dto.response.TeamIdsResponseDto;
import com.link.back.service.TeamBuildingSuggestionService;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
// @RequestMapping("/api/teams")
@Validated
@RequiredArgsConstructor
public class TeamBuildingSuggestionController {

	// private final TeamBuildingSuggestionService teamBuildingSuggestionService;
	//
	// @PostMapping("/{teamId}/members/{userId}/suggest")
	// @ResponseStatus(OK)
	// public void postSuggestionByTeam(@PathVariable @Positive Long teamId, @PathVariable @Positive Long userId) {
	// 	teamBuildingSuggestionService.suggestTeamToUser(teamId, userId);
	// }
	//
	// @DeleteMapping("/{teamId}/members/{userId}/suggest")
	// @ResponseStatus(NO_CONTENT)
	// public void deleteSuggestionByTeam(@PathVariable @Positive Long teamId, @PathVariable @Positive Long userId) {
	// 	teamBuildingSuggestionService.cancelSuggestion(teamId, userId);
	// }
	//
	// @PostMapping("/{teamId}/members/suggest")
	// @ResponseStatus(OK)
	// public void postSuggestionByUser(@PathVariable @Positive Long teamId, @Login @Positive Long userId) {
	// 	teamBuildingSuggestionService.acceptSuggestion(teamId, userId);
	// }
	//
	// @DeleteMapping("/{teamId}/members/suggest")
	// @ResponseStatus(NO_CONTENT)
	// public void deleteSuggestionByUser(@PathVariable @Positive Long teamId, @Login @Positive Long userId) {
	// 	teamBuildingSuggestionService.refuseSuggestion(teamId, userId);
	// }
	//
	// @GetMapping("/{teamId}/suggesting")
	// @ResponseStatus(OK)
	// public CandidatesResponseDto getSuggestionListOfTeam(@PathVariable @Positive Long teamId) { // todo: create dto
	// 	return teamBuildingSuggestionService.getSuggestionListOfTeam(teamId);
	// }
	//
	// @GetMapping("/{teamId}/suggested")
	// @ResponseStatus(OK)
	// public MembersResponseDto getSuggestionListOfUser(@PathVariable @Positive Long teamId,
	// 	@Login @Positive Long userId) { // todo: create dto
	// 	return teamBuildingSuggestionService.getSuggestionListOfUser(teamId, userId);
	// }
	//
	// @GetMapping("/suggested")
	// @ResponseStatus(OK)
	// public TeamIdsResponseDto getSuggestionListOfUser(@Login @Positive Long userId) {
	// 	return teamBuildingSuggestionService.getSuggestionListOfUser(userId);
	// }
}
