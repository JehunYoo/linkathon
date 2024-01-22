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
import lombok.Builder;
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

	@Builder
	public User(Long userId, String email, String password, String phoneNumber, String name, boolean gender,
		LocalDate birth, Integer rating, boolean registered, LocalDate registeredDate, Integer career,
		String referenceUrl,
		String deployUrl, String introduce, Field field, boolean joinState) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.rating = rating;
		this.registered = registered;
		this.registeredDate = registeredDate;
		this.career = career;
		this.referenceUrl = referenceUrl;
		this.deployUrl = deployUrl;
		this.introduce = introduce;
		this.field = field;
		this.joinState = joinState;
	}
}
