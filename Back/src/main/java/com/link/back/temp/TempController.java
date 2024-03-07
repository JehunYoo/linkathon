package com.link.back.temp;

import com.link.back.entity.*;
import com.link.back.repository.TeamRepository;
import com.link.back.repository.UserRepository;
import com.link.back.repository.UserTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Transactional
@RequiredArgsConstructor
public class TempController {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final UserTeamRepository userTeamRepository;

    @PostMapping("/api/temp")
    @ResponseStatus(CREATED)
    public void temp() {
        long userIdMax = 6000L;
        long teamIdMax = 1000L;
        long userTeamIdMax = userIdMax;


        for (long teamId = 1, userId = 1; teamId <= teamIdMax; teamId++) {
            Team team = Team.tempBuilder()
                    .teamId(teamId)
                    .teamStatus(TeamStatus.BUILDING)
                    .build();
            teamRepository.save(team);

            for (int i = 0; i < userIdMax / teamIdMax; i++) {
                User user = User.tempBuilder()
                        .userId(userId)
                        .email("user" + userId + "@example.com")
                        .password("0000")
                        .build();
                userRepository.save(user);

                UserTeam userTeam = UserTeam.tempBuilder()
                        .user(user)
                        .team(team)
                        .role(Role.LEADER)
                        .build();

                userTeamRepository.save(userTeam);

                userId++;
            }
        }
    }

    @PutMapping("/api/temp")
    @ResponseStatus(OK)
    public void temp2() {
        long userIdMax = 6000L;

        for (long userId = 1; userId <= userIdMax; userId++) {
            User user = userRepository.findById(userId).get();
            user.setEmail("user" + userId + "@ssafylink.com");
        }
    }
}
