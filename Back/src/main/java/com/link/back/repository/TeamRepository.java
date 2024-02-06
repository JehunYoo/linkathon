package com.link.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.link.back.entity.MemberStatus;
import com.link.back.entity.Team;
import com.link.back.entity.User;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom {

	@Query(value = "select t from Team t join fetch UserTeam u on t.teamId = u.team.teamId where u.user = :user and u.memberStatus = :status",nativeQuery = true)
	List<Team> findTeamByUserAndStatus(User user, MemberStatus status);

}

//