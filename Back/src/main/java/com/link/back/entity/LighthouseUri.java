package com.link.back.entity;

import static jakarta.persistence.GenerationType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Entity
@Builder
@AllArgsConstructor
public class LighthouseUri {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long lightHouseUriId;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	private String uri;

	public LighthouseUri() {

	}
}
