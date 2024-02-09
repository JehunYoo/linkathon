package com.link.back.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString
public class BackPerformanceMessage {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long messageId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "back_performance_id", nullable = false)
	private BackPerformance backPerformance;

	private String message;

	@Builder
	public BackPerformanceMessage(Long messageId, BackPerformance backPerformance, String message, Long messageCount) {
		this.messageId = messageId;
		this.backPerformance = backPerformance;
		this.message = message;
	}
}
