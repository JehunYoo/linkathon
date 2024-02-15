package com.link.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/test")
public class Test {
	@GetMapping("/")
	ResponseEntity<?> test() {
		return ResponseEntity.ok().build();
	}
}
