package com.link.back.infra.rabbitmq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.link.back.dto.LighthouseReportResponseDTO;
import com.link.back.service.LighthouseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitLightHouse {
	private final RabbitTemplate rabbitTemplate;

	private final LighthouseService lighthouseService;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public void sendMessages(Long projectId) {
		try {
			String json = objectMapper.writeValueAsString(lighthouseService.getUrlUrlList(projectId));
			String combinedMessage = projectId + " " + json;
			rabbitTemplate.convertAndSend("lighthouse_queue", combinedMessage);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@RabbitListener(bindings = @QueueBinding(
		exchange = @Exchange(name = "lighthouse_response", type = ExchangeTypes.TOPIC),
		value = @Queue(name = "lighthouse_response_queue"),
		key = "lighthouse_response_queue")
	)
	public void receiveMessage(String message) {
		try {
			LighthouseReportResponseDTO report = objectMapper.readValue(message, LighthouseReportResponseDTO.class);
			lighthouseService.saveReport(report);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		System.out.println(message);
	}
}