package com.link.back;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.link.back.service.TeamBuildingService;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
class BackApplicationTests {

	@Autowired
	private EntityManager em;

	@Autowired
	private TeamBuildingService teamBuildingService;

	@Test
	void contextLoads() {
	}

}
