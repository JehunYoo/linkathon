package com.link.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.link.back.entity.MemberStatus;
import com.link.back.entity.Team;
import com.link.back.entity.User;
import com.link.back.entity.UserTeam;


@Repository

public interface UserTeamRepository extends JpaRepository<UserTeam, Long> {

	UserTeam findUserTeamByTeamAndUser(Team team, User user);

		// todo
	@Query("select userTeam from UserTeam userTeam "
		// + " join fetch User user"
		// + " join fetch UserImage userImage"
		// + " join fetch UserSkill userSkill join fetch Skill skill"
		+ " where userTeam.team.teamId = :teamId and userTeam.memberStatus = :memberStatus")
		List<UserTeam> findCandidates(@Param("teamId") Long teamId, @Param("memberStatus") MemberStatus memberStatus);

	@Query("select userTeam from UserTeam userTeam join fetch User user join fetch UserImage userImage "
		+ "join fetch UserSkill userSkill join fetch Skill skill "
		+ "where userTeam.team = :team")
	List<UserTeam> findMembers(@Param("team") Team team);

	@Query("select userTeam from UserTeam userTeam join fetch userTeam.team "
		+ "where userTeam.user = :user and userTeam.memberStatus = :memberStatus")
	List<UserTeam> findTeamsOfCandidate(@Param("user") User user, @Param("memberStatus") MemberStatus memberStatus);

	// List<UserTeam> findUserTeamsByTeamAndMemberStatus(Team team, MemberStatus memberStatus);
	// List<UserTeam> findUserTeamsByUserAndMemberStatus(User user, MemberStatus memberStatus);

	void deleteUserTeamByTeamAndUser(Team team, User user);

	List<UserTeam> findUserTeamsByUserAndMemberStatus(User user, MemberStatus status);

	@Query(
		"select userTeam from UserTeam userTeam join fetch userTeam.team team join fetch Hackathon join fetch HackathonImage hackathonImage"
			+ " join fetch UserTeam where team.teamId = :teamId and userTeam.memberStatus = 'JOINED'")
	UserTeam findUserTeamByTeamIdWithTeamAndHackathon(@Param("teamId") Long teamId);

	@Query("select userTeam from UserTeam userTeam"
		// + " join fetch User user join fetch UserImage userImage"
		// + " join fetch UserSkill userSkill join fetch Skill skill"
		+ " where userTeam.team.teamId = :teamId and userTeam.memberStatus = 'JOINED'")
	List<UserTeam> findMembersByTeamId(@Param("teamId") Long teamId);

	@Query("delete from UserTeam u where u.user.userId = :userId and u.team.teamId = :teamId")
	void deleteUserTeamByTeamIdAndUserId(@Param("teamId") Long teamId, @Param("userId") Long userId);

	List<UserTeam> findUserTeamsByTeam(Team team);

	UserTeam findUserTeamByTeamAndUserAndMemberStatus(Team team, User user, MemberStatus status);

	void deleteUserTeamByTeamAndUserAndMemberStatus(Team team, User user, MemberStatus status);

	List<UserTeam> findUserTeamByTeamAndMemberStatus(Team team, MemberStatus memberStatus);

	@Query("select u from UserTeam u where u.user.userId = :userId and u.memberStatus = 'JOINED'")
	Optional<UserTeam> findByIdAndStatus(Long userId);

	// todo
	// @Query("select ut from UserTeam ut join fetch ut.user u join fetch u.userImage ui "
	// 	+ "where ut.team = :team and ut.memberStatus = :memberStatus")
	// List<UserTeam> findCandidates(@Param("team") Team team, @Param("memberStatus") MemberStatus memberStatus);
	//
	// @Query("select ut from UserTeam ut join fetch ut.user u join fetch u.userImage ui "
	// 	+ "where ut.team = :team")
	// List<UserTeam> findMembers(@Param("team") Team team);

	@Query("select u from UserTeam u where u.team.teamId = :teamId")
	List<UserTeam> findUserTeamsByTeamId(Long teamId);

}