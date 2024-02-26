package com.link.back.service;

import static com.link.back.entity.MemberStatus.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.link.back.dto.response.CandidateResponseDto;
import com.link.back.dto.response.CandidatesResponseDto;
import com.link.back.dto.response.MemberResponseDto;
import com.link.back.dto.response.MembersResponseDto;
import com.link.back.dto.response.TeamIdsResponseDto;
import com.link.back.entity.Team;
import com.link.back.entity.User;
import com.link.back.entity.UserSkill;
import com.link.back.entity.UserTeam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@SpringBootTest
@Transactional
class TeamBuildingSuggestionServiceTest {

	// @Autowired
	// private EntityManager em;
	//
	// @Autowired
	// private TeamBuildingSuggestionService service;
	//
	// private Long teamId = 1L;
	// private Long userId = 11L;
	// private Team team;
	// private User user;
	//
	// @BeforeEach
	// void beforeEach() {
	// 	team = em.find(Team.class, teamId);
	// 	user = em.find(User.class, userId);
	//
	// 	service.suggestTeamToUser(teamId, userId);
	// }
	//
	// @Test
	// @DisplayName("팀 참가 제안 정상 요청")
	// void suggestTeamToUser() {
	// 	UserTeam userTeam = em.createQuery("select ut from UserTeam ut "
	// 			+ "where ut.user = :user and ut.team = :team", UserTeam.class)
	// 		.setParameter("user", user)
	// 		.setParameter("team", team)
	// 		.getSingleResult();
	//
	// 	assertThat(userTeam.getMemberStatus()).isEqualTo(SUGGESTED);
	// }
	//
	// @Test
	// @DisplayName("팀 참가 제안 거절 정상 요청")
	// void cancelSuggestion() {
	// 	service.cancelSuggestion(teamId, userId);
	//
	// 	assertThatThrownBy(() -> em.createQuery("select ut from UserTeam ut "
	// 			+ "where ut.user = :user and ut.team = :team", UserTeam.class)
	// 		.setParameter("user", user)
	// 		.setParameter("team", team)
	// 		.getSingleResult())
	// 		.isInstanceOf(NoResultException.class);
	// }
	//
	// @Test
	// @DisplayName("팀 참가 제안 수락 정상 요청")
	// void acceptSuggestion() {
	// 	service.acceptSuggestion(teamId, userId);
	//
	// 	UserTeam userTeam = em.createQuery("select ut from UserTeam ut "
	// 			+ "where ut.user = :user and ut.team = :team", UserTeam.class)
	// 		.setParameter("user", user)
	// 		.setParameter("team", team)
	// 		.getSingleResult();
	//
	// 	assertThat(userTeam.getMemberStatus()).isEqualTo(JOINED);
	// }
	//
	// @Test
	// void refuseSuggestion() {
	// 	service.refuseSuggestion(teamId, userId);
	//
	// 	assertThatThrownBy(() -> em.createQuery("select ut from UserTeam ut "
	// 			+ "where ut.user = :user and ut.team = :team", UserTeam.class)
	// 		.setParameter("user", user)
	// 		.setParameter("team", team)
	// 		.getSingleResult())
	// 		.isInstanceOf(NoResultException.class);
	// }
	//
	// @Test
	// void getSuggestionListOfTeam() {
	// 	CandidatesResponseDto candidatesResponseDto = service.getSuggestionListOfTeam(teamId);
	//
	// 	List<CandidateResponseDto> candidates = candidatesResponseDto.getCandidates();
	//
	// 	CandidateResponseDto candidate = candidates.get(0);
	// 	assertThat(candidate.getName()).isEqualTo(user.getName());
	//
	// 	List<SkillResponseDto> skillSets = candidate.getSkillSets();
	// 	List<String> skillNames = em.createQuery("select us from UserSkill us where us.user = :user", UserSkill.class)
	// 		.setParameter("user", user)
	// 		.getResultList().stream()
	// 		.map(userSkill -> userSkill.getSkill().getSkillName())
	// 		.toList();
	//
	// 	for (SkillResponseDto skillSet : skillSets) {
	// 		assertThat(skillNames.contains(skillSet.getSkillName())).isTrue();
	// 	}
	// }
	//
	// @Test
	// void getSuggestionListOfUser() {
	// 	MembersResponseDto membersResponseDto = service.getSuggestionListOfUser(teamId, userId);
	// 	List<MemberResponseDto> members = membersResponseDto.getMembers();
	//
	// 	List<String> memberNames = em.createQuery("select ut from UserTeam ut where ut.team = :team", UserTeam.class)
	// 		.setParameter("team", team)
	// 		.getResultList().stream()
	// 		.map(userTeam -> userTeam.getUser().getName())
	// 		.toList();
	//
	// 	for (MemberResponseDto member : members) {
	// 		assertThat(memberNames.contains(member.getName())).isTrue();
	// 	}
	// }
	//
	// @Test
	// void getTeamIdsOfSuggestionOfUser() {
	// 	TeamIdsResponseDto suggestedTeams = service.getSuggestionListOfUser(userId);
	//
	// 	for (Long id : suggestedTeams.getTeamIds()) {
	// 		assertThat(id).isEqualTo(teamId);
	// 	}
	// }
}