package com.link.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.link.back.entity.MemberStatus;
import com.link.back.entity.Team;
import com.link.back.entity.User;
import com.link.back.entity.UserTeam;

public interface UserTeamRepository extends JpaRepository<UserTeam, Long>, UserTeamQueryDslRepository {

	UserTeam findUserTeamByTeamAndUser(Team team, User user);

	List<UserTeam> findUserTeamsByTeam(Team team);

	// todo
	@Query("select userTeam from UserTeam userTeam join fetch User user join fetch UserImage userImage "
		+ "join fetch UserSkill userSkill join fetch Skill skill "
		+ "where userTeam.team = :team and userTeam.memberStatus = :memberStatus")
	List<UserTeam> findCandidates(@Param("team") Team team, @Param("memberStatus") MemberStatus memberStatus);

	@Query("select userTeam from UserTeam userTeam join fetch User user join fetch UserImage userImage "
		+ "join fetch UserSkill userSkill join fetch Skill skill "
		+ "where userTeam.team = :team")
	List<UserTeam> findMembers(@Param("team") Team team);

	@Query("select userTeam from UserTeam userTeam join fetch userTeam.team "
		+ "where userTeam.user = :user and userTeam.memberStatus = :memberStatus")
	List<UserTeam> findTeamsOfCandidate(@Param("user") User user, @Param("memberStatus") MemberStatus memberStatus);

	// @Query("select userTeam from UserTeam userTeam join fetch User user join fetch Team team "
	// 	+ "where userTeam.user = :user and userTeam.team = :team")

	void deleteUserTeamByTeamAndUser(Team team, User user);
}
