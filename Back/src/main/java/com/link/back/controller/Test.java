package com.link.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class Test {
	@GetMapping("/test")
	ResponseEntity<?> test() {
		return ResponseEntity.ok("PONG PONG PONG");
	}
}
