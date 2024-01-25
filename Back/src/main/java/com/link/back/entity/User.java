package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

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

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "user_image_id")
	private UserImage userImage;

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


	@OneToMany(mappedBy = "user")
	List<UserSkill> userSkills = new ArrayList<>();

}
