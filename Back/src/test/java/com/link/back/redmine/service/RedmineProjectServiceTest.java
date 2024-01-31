package com.link.back.redmine.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.link.back.redmine.dto.User;
import com.link.back.redmine.dto.request.AddProjectMemberRequest;
import com.link.back.redmine.dto.request.CreateIssueRequest;
import com.link.back.redmine.dto.request.CreateProjectRequest;
import com.link.back.redmine.dto.request.CreateUserRequest;
import com.link.back.redmine.dto.request.UpdateIssueRequest;
import com.link.back.redmine.dto.response.IssueListResponse;

@SpringBootTest
class RedmineProjectServiceTest {

	private final RedmineProjectService redmineProjectService;

	@Autowired
	RedmineProjectServiceTest(RedmineProjectService redmineProjectService) {
		this.redmineProjectService = redmineProjectService;
	}

	@Test
	public void createProjectTest() {
		CreateProjectRequest cpr = new CreateProjectRequest();
		cpr.setName("eee");
		cpr.setIdentifier("ssss");
		redmineProjectService.createProject(cpr);
	}

	@Test
	public void createUserTest() {
		CreateUserRequest cur = new CreateUserRequest(
			"userTest", "password",
			"user", "test", "userTest123@gmail.com");
		redmineProjectService.createUser(cur);
	}

	@Test
	public void findUserListTest() {
		List<User> user = redmineProjectService.findUserList().getUsers().stream().toList();
		for (User u : user) {
			System.out.println("id = " + u.getId());
			System.out.println("login = " + u.getLogin());
		}
	}

	@Test
	public void findProjectListTest() {
		redmineProjectService.findProjectList();
	}

	@Test
	public void addProjectMemberTest() {
		AddProjectMemberRequest apmr = new AddProjectMemberRequest();
		List<Integer> roles = new ArrayList<>();
		roles.add(3);
		apmr.setRoleIds(roles);
		redmineProjectService.addProjectMember(apmr, "userTest", "ssss");
	}

	@Test
	public void createIssueTest() {
		CreateIssueRequest cir = new CreateIssueRequest();
		cir.setSubject("Test입니다.");
		cir.setTrackerId(1L);
		cir.setStatusId(1L);
		cir.setPriorityId(1L);
		redmineProjectService.createIssue(cir, "userTest", "ssss");
	}

	@Test
	public void updateIssueTest() {
		UpdateIssueRequest uir = new UpdateIssueRequest();
		uir.setStatusId(1L);
		uir.setSubject("update issue");
		redmineProjectService.updateIssue(uir, 7L, 5L);
	}

	@Test
	public void findIssueList() {
		IssueListResponse issueList = redmineProjectService.findIssueList(6L);
		System.out.println(issueList.getIssues().size());
		System.out.println(issueList.getIssues().get(1).getSubject());
	}

	@Test
	public void removeIssueListTest() {
		redmineProjectService.removeIssue(5L);
	}

}