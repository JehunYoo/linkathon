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
public class PostFile {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long postFileId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@Column(length = POST_FILE_NAME_LENGTH, nullable = false)
	private String fileName;

	@Column(length = POST_FILE_URL_LENGTH, nullable = false)
	private String fileUrl;

	@Column(length = POST_ORIGIN_FILE_URL_LENGTH)
	private String originFileName;
}
