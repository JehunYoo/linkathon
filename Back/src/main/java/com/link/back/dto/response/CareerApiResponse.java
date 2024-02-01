package com.link.back.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CareerApiResponse {

	private Result result;
	private Data data;

	@Getter
	@Setter
	public static class Result {
		private String code;
		private String extraMessage;
		private String message;
		private String transactionId;
	}
	@Getter
	@Setter
	public static class Data {
		private int jobIndex;
		private int threadIndex;
		private String jti;
		private long twoWayTimestamp;
		private boolean continue2Way;
		private ExtraInfo extraInfo;
		private String method;
	}
	@Getter
	@Setter
	public static class ExtraInfo {
		@JsonProperty("commSimpleAuth")
		private String commSimpleAuth;

	}

}
