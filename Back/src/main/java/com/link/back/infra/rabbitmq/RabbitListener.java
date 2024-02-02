package com.link.back.infra.rabbitmq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.link.back.entity.BackPerformance;
import com.link.back.entity.BackPerformanceMessage;
import com.link.back.entity.Project;
import com.link.back.repository.BackPerformanceMessageRepository;
import com.link.back.repository.BackPerformanceRepository;
import com.link.back.repository.ProjectRepository;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class RabbitListener {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private final BackPerformanceRepository backPerformanceRepository;
	private final BackPerformanceMessageRepository backPerformanceMessageRepository;
	private final ProjectRepository projectRepository;
	private static final Logger log = LoggerFactory.getLogger(RabbitListener.class);
	@org.springframework.amqp.rabbit.annotation.RabbitListener(bindings = @QueueBinding(
		exchange = @Exchange(name="sonarqube_response", type = ExchangeTypes.TOPIC),
		value = @Queue(name="sonarqube_response_queue"),
		key = "sonarqube_response_queue")
	)
	public void receiveMessage(String message) {
		// result 저장
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(message);
			Long projectId = Long.parseLong(jsonNode.get("project_id").asText());
			// messages 배열 가져오기
			JsonNode messageResponseNode = objectMapper.readTree(jsonNode.get("message_response").asText());
			System.out.println(messageResponseNode);
			System.out.println("hi");
			JsonNode issues = messageResponseNode.get("issues");
			System.out.println(issues);
			// measures 배열 가져오기
			JsonNode responseNode = objectMapper.readTree(jsonNode.get("type_response").asText());
			JsonNode measuresNode = responseNode.get("measures");
			int bugs = 0;
			int codeSmells = 0;
			double coverage = 0.0;
			double duplications = 0.0;
			double securityRating = 0.0;
			double vulnerabilities = 0.0;

			for (JsonNode measure : measuresNode) {
				String metric = measure.get("metric").asText();
				String value = measure.get("value").asText();
				switch (metric) {
					case "bugs" :
						bugs = Integer.parseInt(value);
						break;
					case "code_smells" :
						codeSmells = Integer.parseInt(value);
						break;
					case "coverage" :
						coverage = Double.parseDouble(value);
						break;
					case "duplicated_lines_density" :
						duplications = Double.parseDouble(value);
						break;
					case "security_rating" :
						securityRating = Double.parseDouble(value);
						break;
					case "vulnerabilities" :
						vulnerabilities = Double.parseDouble(value);
						break;
				}
			}

			Project project = projectRepository.getReferenceById(projectId);
			BackPerformance backPerformance = BackPerformance.builder().bugs(bugs).codeSmells(codeSmells).coverage(coverage).duplications(duplications)
				.securityRating(securityRating).vulnerabilities(vulnerabilities).project(project).build();
			backPerformanceRepository.save(backPerformance);
			System.out.println("저장");
			System.out.println(issues.asText());
			for (JsonNode issue : issues) {
				String msg = issue.get("message").asText();
				System.out.println("msg" + issue.asText());
				BackPerformanceMessage backPerformanceMessage = BackPerformanceMessage.builder().backPerformance(backPerformance)
					.message(msg).build();
				System.out.println("hi");
				backPerformanceMessageRepository.save(backPerformanceMessage);
			}
		}catch(Exception e){
				e.printStackTrace(); // Handle the exception appropriately
			}

	}
	@Getter
	private static class Measure {
		private String metric;

		private String value;

		private String component;

		private boolean bestValue;

	}
}
