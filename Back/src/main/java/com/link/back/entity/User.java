package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Collection;

import lombok.Builder;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class User implements UserDetails {

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


	public User(User user) {
		this.userId = user.userId;
		this.email = user.email;
		this.password = user.password;
		this.phoneNumber = user.phoneNumber;
		this.name = user.name;
		this.gender = user.gender;
		this.birth = user.birth;
		this.rating = user.rating;
		this.registered = user.registered;
		this.registeredDate = user.registeredDate;
		this.career = user.career;
		this.referenceUrl = user.referenceUrl;
		this.deployUrl = user.deployUrl;
		this.introduce = user.introduce;
		this.field = user.field;
		this.joinState = user.joinState;
	}


	// 비밀번호 변경 메소드
	public User withEncryptedPassword(String password, String secretKey) {
		User newUser = new User(this);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom(	secretKey.getBytes()));
		newUser.password = passwordEncoder.encode(password);
		return newUser;
	}
//	@Builder
//	public User(String email, String password) {
//		this.email = email;
//		this.password = password;
//	}

	@Builder
	public User(String email, String password, String name, boolean gender, LocalDate birth, String phoneNumber, int rating) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.phoneNumber = phoneNumber;
		this.rating = rating;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	//비밀번호 변경 메소드
	public void updatePassword(String password){
		this.password = password;
	}
}
