package com.link.back.infra.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.link.back.dto.response.ProjectResponseDto;
import com.link.back.entity.Project;
import com.link.back.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitPublisher {
	private final RabbitTemplate rabbitTemplate;
	private final ProjectRepository projectRepository;

	public void sendMessages(Long projectId) {
		// todo : Project 테이블에서 gitUrl 가져오는 로직 테스트

		String gitUrl = projectRepository.findById(projectId).get().getProjectUrl();
		// String gitUrl = "https://github.com/jooyun-1/Quicklog";
		String combinedMessage = projectId + " " + gitUrl;
		rabbitTemplate.convertAndSend("sonarqube_queue", combinedMessage);
	}

	@Scheduled(cron = "0 1 10 * * *")  // 매일 자정에 실행
	public void sendMessagesDaily() {
		Iterable<Project> projects = projectRepository.findAll();
		for (Project project : projects) {
			sendMessages(project.getProjectId());
		}
	}


}
