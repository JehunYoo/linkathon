package com.link.back.dto.response;

import java.util.List;

import lombok.Getter;

@Getter
public class ApiDetailResponse {

	private Result result;
	private List<Data> data;
	@Getter
	public static class Result {
		private String code;
		private String extraMessage;
		private String message;
		private String transactionId;

		// Getter and Setter methods


	}
	@Getter
	public static class Data {
		private String resIssueNo;
		private String resUserNm;
		private String resUserIdentiyNo;
		private String resJoinUserType;
		private String resCompanyNm;
		private String commStartDate;
		private String commEndDate;
		private String resIssueDate;
		private String resOriGinalData;

		// Getter and Setter methods

	}

}
