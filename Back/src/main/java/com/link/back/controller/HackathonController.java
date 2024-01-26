package com.link.back.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.link.back.dto.request.HackathonRequest;
import com.link.back.dto.response.HackathonResponseDto;
import com.link.back.dto.response.HackathonsResponseDto;
import com.link.back.dto.response.WinnerProjectResponseDto;
import com.link.back.entity.Hackathon;
import com.link.back.service.HackathonService;

import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hackathons")
public class HackathonController {
	private final HackathonService hackathonService;
	@PostMapping
	public ResponseEntity<Void> createHackathon(@RequestBody HackathonRequest hackathonRequest) {
		hackathonService.createHackathon(hackathonRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@GetMapping("/{hackathonId}")
	public ResponseEntity<HackathonResponseDto> getHackathonInfo(@PathVariable Long hackathonId) {
		HackathonResponseDto hackathonResponseDto = hackathonService.getHackathonInfo(hackathonId);
		return new ResponseEntity<>(hackathonResponseDto,HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<Page<HackathonsResponseDto>> getAllHackathonInfo(@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,@Param("status") String status) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("registerDate").descending());
		return new ResponseEntity<>(hackathonService.getAllHackathonInfo(pageable,status),HttpStatus.OK);
	}
	// @GetMapping("/api/hackathons/{hackathonId}/winners")
	// public ResponseEntity<List<WinnerProjectResponseDto>>
	@PutMapping("/{hackathonId}")
	public ResponseEntity<Void> updateHackathonInfo(@PathVariable Long hackathonId, @RequestBody HackathonRequest hackathonRequest) {
		hackathonService.updateHackathon(hackathonId,hackathonRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping("/{hackathonId}")
	public ResponseEntity<Void> deleteHackathonInfo(@PathVariable Long hackathonId) {
		hackathonService.deleteHackathon(hackathonId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
