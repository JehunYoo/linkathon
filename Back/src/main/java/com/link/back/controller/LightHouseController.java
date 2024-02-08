package com.link.back.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.link.back.dto.response.LighthouseResponseDTO;
import com.link.back.infra.rabbitmq.RabbitLightHouse;
import com.link.back.service.LighthouseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lighthouse")
public class LightHouseController {
	private final RabbitLightHouse rabbitLightHouse;
	private final LighthouseService lighthouseService;

	@PostMapping("/report/{projectId}")
	public ResponseEntity<Void> updateReport(@PathVariable Long projectId) {
		rabbitLightHouse.sendMessages(projectId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/report/{projectId}")
	public ResponseEntity<List<LighthouseResponseDTO>> getReportList(@PathVariable Long projectId) {
		return ResponseEntity.ok(lighthouseService.getReportList(projectId));
	}

	@PostMapping("/uri/{projectId}")
	public ResponseEntity<Void> updateUri(@PathVariable Long projectId, @RequestBody List<String> uriList) {
		lighthouseService.saveUri(projectId, uriList);
		return ResponseEntity.ok().build();
	}
}
