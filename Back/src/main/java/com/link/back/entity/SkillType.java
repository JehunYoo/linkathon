package com.link.back.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SkillType {
	LANGUAGE("언어"),
	FRONTEND("프론트엔드"),
	BACKEND("백엔드"),
	TESTING("테스팅 툴"),
	DATABASE("데이터베이스"),
	DATA("데이터"),
	DEVOPS("데브옵스"),
	TOOL("협업툴"),
	DESIGN("디자인");

	private final String name;
}
