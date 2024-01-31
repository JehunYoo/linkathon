package com.link.back.infra.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

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
}
