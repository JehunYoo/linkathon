package com.link.back.controller;

import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.link.back.infra.rabbitmq.RabbitPublisher;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

		private final RabbitPublisher rabbitPublisher;

		// todo : Post API 테스트

		@PostMapping("/{project_id}/back-metrics")
		public ResponseEntity<Void> sendMessage(@PathVariable Long project_id) {

			rabbitPublisher.sendMessages(project_id);

			return new ResponseEntity<>(HttpStatus.OK);
		}

		// todo : BackPerformance Table 데이터 가져오는 API 구현
		//@GetMapping
	}

