package com.link.back.entity;

import static com.link.back.config.AppConstant.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Post {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long postId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	@Column(length = POST_TITLE_LENGTH, nullable = false)
	private String postTitle;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String postContent;
}
