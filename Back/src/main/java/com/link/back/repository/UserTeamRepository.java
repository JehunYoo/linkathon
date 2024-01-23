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
	@Query("select ut from UserTeam ut join fetch User u join fetch UserImage ui "
		+ "where ut.team = :team and ut.memberStatus = :memberStatus")
	List<UserTeam> findCandidates(@Param("team") Team team, @Param("memberStatus") MemberStatus memberStatus);

	@Query("select ut from UserTeam ut join fetch User u join fetch UserImage ui "
		+ "where ut.team = :team")
	List<UserTeam> findMembers(@Param("team") Team team);

	@Query("select ut from UserTeam ut join fetch ut.team "
		+ "where ut.user = :user and ut.memberStatus = :memberStatus")
	List<UserTeam> findTeamsOfCandidate(@Param("user") User user, @Param("memberStatus") MemberStatus memberStatus);

	void deleteUserTeamByTeamAndUser(Team team, User user);
}
