package com.link.back.dto.request;

import io.codef.api.EasyCodef;
import lombok.Getter;

@Getter
public class UseApiRequest {

	//간편인증 로그인 구분 (1:카카오톡, 2:페이코, 3:삼성패스, 4:KB모바일, 5:통신사(PASS), 6:네이버, 7:신한인증서, 8: toss)
	private String loginTypeLevel;
	//가입자구분(용도구분), "0" : 전체, "1" : 직장가입자, "2" : 지역가입자, "3" : 가입자 전체
	private String useType;
	//이름
	private String userName;
	//생년월일 8자리
	private String identity;
	//통신사 “0":SKT(SKT알뜰폰), “1”:KT(KT알뜰폰), “2":LG U+(LG U+알뜰폰)
	private String telecom;
	// 핸드폰번호
	private String phoneNo;

}
