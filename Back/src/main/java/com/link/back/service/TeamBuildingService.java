package com.link.back.service;

import static com.link.back.entity.MemberStatus.*;
import static com.link.back.entity.Role.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.back.dto.request.CreateTeamRequestDto;
import com.link.back.dto.response.CandidatesResponseDto;
import com.link.back.dto.response.TeamApplicationResponseDto;
import com.link.back.entity.MemberStatus;
import com.link.back.entity.Role;
import com.link.back.entity.Team;
import com.link.back.entity.TeamStatus;
import com.link.back.entity.User;
import com.link.back.entity.UserTeam;
import com.link.back.repository.HackathonRepository;
import com.link.back.repository.TeamRepository;
import com.link.back.repository.TeamSkillRepository;
import com.link.back.repository.UserRepository;
import com.link.back.repository.UserTeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamBuildingService {

	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	private final UserTeamRepository userTeamRepository;


	public void teamParticipate(Long teamId, Long userId, String status) {
		Team team = teamRepository.findById(teamId).orElseThrow(RuntimeException::new);
		User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
		UserTeam userTeam = UserTeam.builder()
			.user(user)
			.team(team)
			.role(CANDIDATE)
			.memberStatus(MemberStatus.valueOf(status))
			.build();
		userTeamRepository.save(userTeam);
	}

	public void refuseOrCancelTeamParticipate(Long teamId, Long userId) {
		Team team = teamRepository.findById(teamId)
			.orElseThrow(RuntimeException::new);// todo: create exception

		User user = userRepository.findById(userId)
			.orElseThrow(RuntimeException::new); // todo: create exception

		userTeamRepository.deleteUserTeamByTeamAndUser(team, user);

		// todo: notification!!!
	}

	public void acceptTeamParticipate(Long teamId, Long userId) {
		Team team = teamRepository.findById(teamId)
			.orElseThrow(RuntimeException::new);// todo: create exception

		User user = userRepository.findById(userId)
			.orElseThrow(RuntimeException::new); // todo: create exception

		UserTeam userTeam = userTeamRepository.findUserTeamByTeamAndUser(team, user);
		userTeam.joinTeam();

		// todo: notification!!!
	}

	@Transactional(readOnly = true)
	public CandidatesResponseDto getSuggestionListOfTeam(Long teamId, MemberStatus status) {
		Team team = teamRepository.findById(teamId)
			.orElseThrow(RuntimeException::new); // todo: create exception

		List<UserTeam> candidates = userTeamRepository.findCandidates(team, status);

		return new CandidatesResponseDto(candidates);
	}

	public TeamApplicationResponseDto getTeamParticipateList(Long userId, Long teamId, MemberStatus status) {
		User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
		// 팀 리스트(teamId, teamName)
		List<Team> teams = teamRepository.findTeamByUserAndStatus(user, status);
		if (teamId == null)
			teamId = teams.get(0).getTeamId();
		// 팀 정보(teamName, teamDesc), 해커톤 정보
		UserTeam userTeam = userTeamRepository.findUserTeamByTeamIdWithTeamAndHackathon(teamId);
		// 팀 멤버 정보(List<TeamMemberResponseDto>
		List<UserTeam> members = userTeamRepository.findMembersByTeamId(teamId);
		return new TeamApplicationResponseDto(teams, userTeam, members);
	}

	public void leaveTeamMember(Long teamId, Long userId) {
		userTeamRepository.deleteUserTeamByTeamIdAndUserId(teamId, userId);
	}

	public void createTeam(CreateTeamRequestDto createTeamRequestDto, Long userId) {
		Team team = Team.builder()
			.teamName(createTeamRequestDto.getTeamName())
			.teamStatus(TeamStatus.BUILDING)
			.teamMember(0)
			.teamDesc(createTeamRequestDto.getTeamDesc())
			.build();
		teamRepository.save(team);
		User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
		UserTeam userTeam = UserTeam.builder()
			.user(user)
			.team(team)
			.role(LEADER)
			.memberStatus(JOINED)
			.build();
		userTeamRepository.save(userTeam);
	}

	public void deleteTeam(Long teamId) {
		Team team = teamRepository.findById(teamId).orElseThrow(RuntimeException::new);
		TeamStatus teamStatus = team.getTeamStatus();

		// 팀 삭제 userTeam cascade remove로 자동 삭제
		teamRepository.delete(team);

		if(teamStatus.equals(TeamStatus.BUILDING)) { // 팀 빌딩 완료 상태일 때
			// 프로젝트 삭제

			// user 프로젝트 참가 여부 true로 변경

		} else { // 팀 빌딩 미 완료 상태일 때

		}
	}
}
