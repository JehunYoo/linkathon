package com.link.back.infra.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.link.back.dto.response.ProjectResponseDto;
import com.link.back.entity.Project;
import com.link.back.repository.BackPerformanceMessageRepository;
import com.link.back.repository.BackPerformanceRepository;
import com.link.back.repository.ProjectRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class RabbitPublisher {
	private final RabbitTemplate rabbitTemplate;
	private final ProjectRepository projectRepository;
	private final BackPerformanceRepository backPerformanceRepository;
	private final BackPerformanceMessageRepository backPerformanceMessageRepository;
	public void sendMessages(Long projectId) {
		String gitUrl = projectRepository.findById(projectId).get().getProjectUrl();
		String combinedMessage = projectId + " " + gitUrl;
		rabbitTemplate.convertAndSend("sonarqube_queue", combinedMessage);
	}

	@Scheduled(cron = "0 32 16 * * *", zone = "Asia/Seoul")  // 매일 자정에 실행
	public void sendMessagesDaily() {
		backPerformanceMessageRepository.deleteAll();
		backPerformanceRepository.deleteAll();
		Iterable<Project> projects = projectRepository.findAll();
		for (Project project : projects) {
			sendMessages(project.getProjectId());
		}
	}

}
