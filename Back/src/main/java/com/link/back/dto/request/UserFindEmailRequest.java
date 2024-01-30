package com.link.back.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class UserFindEmailRequest {

	private String name;
	private LocalDate birth;
	private String phoneNumber;

}
