package com.link.back.dto.request;

import lombok.Getter;

@Getter
public class UseApiRequest {

	//이름
	private String userName;
	//생년월일 8자리
	private String identity;
	//통신사 “0":SKT(SKT알뜰폰), “1”:KT(KT알뜰폰), “2":LG U+(LG U+알뜰폰)
	private String telecom;
	// 핸드폰번호
	private String phoneNo;

}
