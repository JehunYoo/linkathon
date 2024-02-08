package com.link.back.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LighthouseReportResponseDTO {
	private Long projectId;
	private List<LighthouseReportResponseDataDTO> data;
}
