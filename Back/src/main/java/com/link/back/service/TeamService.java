package com.link.back.service;

import static com.link.back.entity.Role.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.back.entity.Team;
import com.link.back.entity.User;
import com.link.back.entity.UserTeam;
import com.link.back.repository.TeamRepository;
import com.link.back.repository.UserRepository;
import com.link.back.repository.UserTeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {

	@Value("${project.url}")
	private String url;

	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	private final UserTeamRepository userTeamRepository;

	private final EmailService emailService; // todo: user Async

	private final RedisTemplate<String, String> redisTemplate;

	public void requestToRemoveMember(Long teamId, Long excludedMemberId, Long leaderId) {
		Team team = teamRepository.findById(teamId)
			.orElseThrow(RuntimeException::new);// todo: create exception

		User loginUser = userRepository.findById(leaderId)
			.orElseThrow(RuntimeException::new); // todo: create exception

		UserTeam leader = userTeamRepository.findUserTeamByTeamAndUser(team, loginUser);

		if (leader == null || leader.getRole() != LEADER) {
			throw new RuntimeException(); // todo: create exception
		}

		User excludedUser = userRepository.findById(excludedMemberId)
			.orElseThrow(RuntimeException::new); // todo: create exception

		UserTeam excludedMember = userTeamRepository.findUserTeamByTeamAndUser(team, excludedUser);

		List<UserTeam> members = userTeamRepository.findUserTeamsByTeam(team);
		members.remove(excludedMember);
		members.remove(leader);

		Map<UserTeam, UUID> uuids = new HashMap<>();
		members.forEach(member -> uuids.put(member, UUID.randomUUID()));

		// save uuid at redis and send email
		HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();

		for (Map.Entry<UserTeam, UUID> entry : uuids.entrySet()) {
			UserTeam member = entry.getKey();
			UUID uuid = entry.getValue();

			operations.put(excludedMemberId.toString(), member.getUserTeamId(), uuid.toString());

			emailService.sendEmail(null, member.getUser().getEmail(),
				"팀원 " + excludedMember.getUser().getName() + " 삭제 허가 요청",
				getRemoveMemberEmailContent(url, teamId, excludedMemberId, member.getUserTeamId(), uuid), false);
		}
	}

	// todo: change target url localhost:8080
	private String getRemoveMemberEmailContent(String url, Long teamId, Long excludedMemberId, Long memberId, UUID uuid) {
		return "<!doctype html>\n"
			+ "<html lang=\"ko\">\n"
			+ "<head>\n"
			+ "    <meta charset=\"UTF-8\">\n"
			+ "    <meta name=\"viewport\"\n"
			+ "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n"
			+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n"
			+ "    <title>팀원 삭제 허가 요청</title>\n"
			+ "</head>\n"
			+ "<body>\n"
			+ "    <form method=\"post\" action=\"" + url + "/api/teams/" + teamId + "/members/" + excludedMemberId + "/permission/remove\">\n"
			+ "        <input type=\"hidden\" name=\"memberId\" id=\"memberId\" value=\"" + memberId + "\" />\n"
			+ "        <input type=\"hidden\" name=\"uuid\" id=\"uuid\" value=\"" + uuid + "\" />\n"
			+ "        <button type=\"submit\">Permit Request</button>\n"
			+ "    </form>\n"
			+ "</body>\n"
			+ "</html>";
	}

	public void permitToRemoveMember(Long teamId, Long excludedMemberId, Long memberId, String uuid) {
		HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
		String savedUuid = (String)operations.get(excludedMemberId.toString(), memberId.toString());

		if (savedUuid != null && savedUuid.equals(uuid)) {
			operations.delete(excludedMemberId.toString(), memberId.toString());
		}

		if (operations.size(excludedMemberId.toString()) == 0) {
			Team team = teamRepository.findById(teamId)
				.orElseThrow(RuntimeException::new);// todo: create exception
			User excludedUser = userRepository.findById(excludedMemberId)
				.orElseThrow(RuntimeException::new); // todo: create exception

			removeMember(team, excludedUser);
		}
	}

	private void removeMember(Team team, User user) {
		userTeamRepository.deleteUserTeamByTeamAndUser(team, user);
	}
}
