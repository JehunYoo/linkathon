package com.link.back.redmine.service;

import static java.nio.charset.StandardCharsets.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.link.back.redmine.RedmineProperties;
import com.link.back.redmine.dto.User;
import com.link.back.redmine.dto.request.AddProjectMembersRequest;
import com.link.back.redmine.dto.request.CreateIssueRequest;
import com.link.back.redmine.dto.request.CreateProjectRequest;
import com.link.back.redmine.dto.request.AddProjectMemberRequest;
import com.link.back.redmine.dto.request.CreateUserRequest;
import com.link.back.redmine.dto.request.Request;
import com.link.back.redmine.dto.request.UpdateIssueRequest;
import com.link.back.redmine.dto.response.IssueListResponse;
import com.link.back.redmine.dto.response.ProjectListResponse;
import com.link.back.redmine.dto.response.ProjectResponse;
import com.link.back.redmine.dto.response.Response;
import com.link.back.redmine.dto.response.UserListResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedmineProjectService {

	private final RedmineProperties redmineProperties;

	public void createProject(CreateProjectRequest cpr) {
		postRequest(cpr, "/projects.json", "project");
	}

	public void createUser(CreateUserRequest cur) {
		postRequest(cur, "/users.json", "user");
	}

	public UserListResponse findUserList() {
		return (UserListResponse)getRequest("/users.json", "userList");
	}

	public ProjectListResponse findProjectList() {
		return (ProjectListResponse)getRequest("/projects.json", "projectList");
	}

	public void addProjectMembers(List<String> logins, String identifier) {
		Long projectId = findProjectId(identifier);
		for (String login : logins) {
			addProjectMember(new AddProjectMemberRequest(), login, projectId);
		}

	}

	public void addProjectMember(AddProjectMemberRequest apmr, String login, Long projectId) {
		apmr.setUserId(findUserId(login));
		String apiUrl = "/projects/" + projectId + "/memberships.json";
		postRequest(apmr, apiUrl, "membership");
	}

	public void createIssue(CreateIssueRequest cir, String login, String identifier) {
		cir.setProjectId(findProjectId(identifier));
		cir.setAssignedToId(findUserId(login));
		postRequest(cir, "/issues.json", "issue");
	}

	public void updateIssue(UpdateIssueRequest uir, Long issueId) {
		String apiUrl = "/issues/" + issueId + ".json";
		putRequest(uir, apiUrl, "issue");
	}

	public IssueListResponse findIssueList(Long projectId) {
		return (IssueListResponse)getRequest("/issues.json?project_id=" + projectId, "issueList");
	}

	public void removeIssue(Long issueId) {
		deleteRequest("/issues/" + issueId + ".json");
	}

	public Long findUserId(String login) {
		List<User> users = findUserList().getUsers();
		for (User user : users) {
			if (user.getLogin().equals(login)) {
				return user.getId();
			}
		}
		return 0L;
	}

	public Long findProjectId(String identifier) {
		List<ProjectResponse> projects = findProjectList().getProjects();
		for (ProjectResponse project : projects) {
			if (project.getIdentifier().equals(identifier)) {
				return project.getId();
			}
		}
		return 0L;
	}

	public Response getRequest(String apiUrl, String respType) {
		HttpURLConnection conn = null;
		try {
			conn = getConnection("GET", apiUrl);
			return readData(conn, respType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public void deleteRequest(String apiUrl) {
		HttpURLConnection conn = null;
		try {
			conn = getConnection("DELETE", apiUrl);
			sendData(conn, "");
			responseData(conn);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public void putRequest(Request cur, String apiUrl, String dataCover) {
		ObjectMapper objectMapper = new ObjectMapper();
		HttpURLConnection conn = null;
		try {
			String data = objectMapper.writeValueAsString(cur);
			conn = getConnection("PUT", apiUrl);
			sendData(conn, makeJsonData(data, dataCover));
			responseData(conn);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public void postRequest(Request cur, String apiUrl, String dataCover) {
		ObjectMapper objectMapper = new ObjectMapper();
		HttpURLConnection conn = null;
		try {
			String data = objectMapper.writeValueAsString(cur);
			conn = getConnection("POST", apiUrl);
			sendData(conn, makeJsonData(data, dataCover));
			responseData(conn);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public HttpURLConnection getConnection(String requestType, String apiUrlPlus) throws IOException {
		String apiUrl = redmineProperties.getApiUrl() + apiUrlPlus;
		String apiKey = redmineProperties.getApiKey();
		// URL 설정
		URL url = new URL(apiUrl);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		// 요청 메서드 설정
		conn.setRequestMethod(requestType);
		conn.setDoOutput(true);
		// API 키 및 컨텐트 타입 설정
		conn.setRequestProperty("X-Redmine-API-key", apiKey);
		conn.setRequestProperty("Content-Type", "application/json");

		return conn;
	}

	public void sendData(HttpURLConnection conn, String jsonData) throws IOException {
		OutputStream os = conn.getOutputStream();
		byte[] input = jsonData.getBytes(UTF_8);
		os.write(input, 0, input.length);
	}

	public Response readData(HttpURLConnection conn, String respType) throws IOException {
		BufferedReader br = new BufferedReader(
			new InputStreamReader(conn.getInputStream(), UTF_8));
		StringBuilder response = new StringBuilder();
		String responseLine;

		while ((responseLine = br.readLine()) != null) {
			response.append(responseLine.trim());
		}

		ObjectMapper objectMapper = new ObjectMapper();

		return switch (respType) {
			case "userList" -> objectMapper.readValue(response.toString(), UserListResponse.class);
			case "projectList" -> objectMapper.readValue(response.toString(), ProjectListResponse.class);
			case "issueList" -> objectMapper.readValue(response.toString(), IssueListResponse.class);
			default -> null;
		};

	}

	public void responseData(HttpURLConnection conn) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF_8));
		StringBuilder sb = new StringBuilder();
		String responseLine;
		while ((responseLine = br.readLine()) != null) {
			sb.append(responseLine.trim());
		}
		// return sb.toString();
	}

	public String makeJsonData(String data, String dataCover) {
		return "{\"" + dataCover + "\":" + data + "}";
	}

}
