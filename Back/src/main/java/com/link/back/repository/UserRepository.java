package com.link.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.link.back.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u from User u"
		+ " join f")
	List<User> findAllByuserId(@Param("userId") Long userId);

}
