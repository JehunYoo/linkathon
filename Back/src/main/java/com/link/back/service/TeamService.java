package com.link.back.service;

import static com.link.back.entity.Role.*;

import java.util.List;
import java.util.UUID;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.back.dto.request.PermissionToRemoveMemberRequestDto;
import com.link.back.entity.Team;
import com.link.back.entity.User;
import com.link.back.entity.UserTeam;
import com.link.back.repository.TeamRepository;
import com.link.back.repository.UserRepository;
import com.link.back.repository.UserTeamRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {

	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	private final UserTeamRepository userTeamRepository;

	private final RedisTemplate<String, String> redisTemplate;

	// private final JavaMailSender javaMailSender;

	public void requestToRemoveMember(Long teamId, Long excludedMemberId, Long leaderId) {
		// Team team = teamRepository.findById(teamId)
		// 	.orElseThrow(RuntimeException::new);// todo: create exception
		//
		// User loginUser = userRepository.findById(leaderId)
		// 	.orElseThrow(RuntimeException::new); // todo: create exception
		//
		// UserTeam leader = userTeamRepository.findUserTeamByTeamAndUser(team, loginUser);
		//
		// if (leader == null || leader.getRole() != LEADER) {
		// 	throw new RuntimeException(); // todo: create exception
		// }
		//
		// User excludedUser = userRepository.findById(excludedMemberId)
		// 	.orElseThrow(RuntimeException::new); // todo: create exception
		//
		// UserTeam excludedMember = userTeamRepository.findUserTeamByTeamAndUser(team, excludedUser);
		//
		// List<UserTeam> members = userTeamRepository.findUserTeamsByTeam(team);
		// members.remove(excludedMember);
		// members.remove(leader);
		//
		// String[] uuids = (String[]) members.stream()
		// 	.map(member -> member.getUserTeamId() + "$" + UUID.randomUUID())
		// 	.toArray();
		//
		// // todo: redis
		// SetOperations<String, String> operations = redisTemplate.opsForSet();
		// operations.add(excludedMemberId + "@" + teamId, uuids);
		//
		// // todo: email
		//
		// for (int i = 0; i < members.size(); i++) {
		// 	UserTeam member = members.get(i);
		// 	String email = member.getUser().getEmail();
		// 	String uuid = uuids[i];
		//
		// 	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		//
		// 	try {
		// 		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
		// 		mimeMessageHelper.setTo(email);
		// 		mimeMessageHelper.setSubject("팀원 " + excludedMemberId + " 삭제 허가 요청");
		// 		mimeMessageHelper.setText(getEmailContent(teamId, excludedMemberId, uuid));
		// 		javaMailSender.send(mimeMessage);
		//
		// 	} catch (MessagingException e) {
		// 		throw new RuntimeException(); // todo: create Exception
		// 	}
		// }
	}

	private String getEmailContent(Long teamId, Long excludedMemberId, String uuid) {
		return "<!doctype html>\n"
			+ "<html lang=\"en\">\n"
			+ "<head>\n"
			+ "    <meta charset=\"UTF-8\">\n"
			+ "    <meta name=\"viewport\"\n"
			+ "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n"
			+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n"
			+ "    <title>Email</title>\n"
			+ "</head>\n"
			+ "<body>\n"
			+ "    <form action=\"post\""
			+ " target=\"localhost:8080/api/teams/" + teamId + "/members/" + excludedMemberId + "/permission/remove"
			+ "?uuid=" + uuid + "\">\n"
			+ "        <button type=\"submit\">Permit Request</button>\n"
			+ "    </form>\n"
			+ "</body>\n"
			+ "</html>";
	}

	public void permitToRemoveMember(Long teamId, Long excludedMemberId, Long memberId,
		PermissionToRemoveMemberRequestDto requestDto) {

		// todo: !!! no memberId

		SetOperations<String, String> operations = redisTemplate.opsForSet();
		String key = excludedMemberId + "@" + teamId;
		String value = memberId + "$" + requestDto.getUuid();

		if (Boolean.TRUE.equals(operations.isMember(key, value))) {
			operations.remove(key, value);
		}

		if (Boolean.TRUE.equals(operations.getOperations().hasKey(key)) && operations.size(key) == 0) {
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
