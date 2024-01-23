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
import com.link.back.entity.Project;
import com.link.back.repository.BackPerformanceRepository;
import com.link.back.repository.ProjectRepository;
import com.nimbusds.jose.shaded.gson.JsonObject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitListener {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private final BackPerformanceRepository backPerformanceRepository;
	private final ProjectRepository projectRepository;
	private static final Logger log = LoggerFactory.getLogger(RabbitListener.class);
	@org.springframework.amqp.rabbit.annotation.RabbitListener(bindings = @QueueBinding(
		exchange = @Exchange(name="sonarqube_response", type = ExchangeTypes.TOPIC),
		value = @Queue(name="sonarqube_response_queue"),
		key = "sonarqube_response_queue")
	)
	public void receiveMessage(String message) {
		log.info(message);
		// result 저장


		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(message);
			Long projectId = Long.parseLong(jsonNode.get("project_id").asText());
			// Map<String, ArrayList<Measure>> backPerformanceMap = objectMapper.readValue(
			// 	message,
			// 	new TypeReference<Map<String, ArrayList<Measure>>>() {}
			// );
			// measures 배열 가져오기
			JsonNode responseNode = objectMapper.readTree(jsonNode.get("response").asText());
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
				// 여기에서 필요한 작업 수행
			}
			//todo : Project Data 추가될 시, backPerformance와 매핑

			// Project project = projectRepository.getReferenceById(projectId);
			BackPerformance backPerformance = BackPerformance.builder().bugs(bugs).codeSmells(codeSmells).coverage(coverage).duplications(duplications)
				.securityRating(securityRating).vulnerabilities(vulnerabilities).build();
			// System.out.println(backPerformance);
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
