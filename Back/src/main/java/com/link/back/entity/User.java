package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long userId;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(length = PHONENUMBER_LENGTH, nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private boolean gender;

	@Column(nullable = false)
	private LocalDate birth;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Integer rating; // 티어 점수

	@Column(nullable = false)
	@ColumnDefault("false")
	private boolean registered;

	private LocalDate registeredDate;

	private Integer career;

	private String referenceUrl;

	private String deployUrl;

	@Column(length = USER_INTRODUCE)
	private String introduce;

	@Enumerated(STRING)
	private Field field;

	@Column(nullable = false)
	@ColumnDefault("false")
	private boolean joinState; // 프로젝트 참가 여부
}
