package com.link.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.link.back.dto.response.TeamApplicationResponseDto;
import com.link.back.entity.MemberStatus;
import com.link.back.entity.Team;
import com.link.back.entity.User;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("select t from Team t join fetch UserTeam u where u.user = :user and u.memberStatus = :status")
	List<Team> findTeamByUserAndStatus(User user, MemberStatus status);

}
