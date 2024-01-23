package com.link.back.infra.rabbitmq.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
	파일 이름, 심각성, 코드 라인, 메시지, 타입
 */
@Data
public class SonarqubeResponse {
	private int total;
	private int p;
	private int ps;
	private Paging paging;
	private int effortTotal;
	private List<Issue> issues;
	private List<Component> components;
}

@Data
class Paging {
	private int pageIndex;
	private int pageSize;
	private int total;

	// getters and setters
}

@Data
class Issue {
	private String key;
	private String rule;
	private String severity;
	private String component;
	private String project;
	private int line;
	private String hash;
	private TextRange textRange;
	private List<Object> flows; // Adjust if the structure of flows is known
	private String status;
	private String message;
	private String effort;
	private String debt;
	private String author;
	private List<String> tags;
	private Date creationDate;
	private Date updateDate;
	private String type;
	private String scope;
	private boolean quickFixAvailable;
	private List<Object> messageFormattings; // Adjust if the structure of messageFormattings is known
	private List<Object> codeVariants; // Adjust if the structure of codeVariants is known
	private String cleanCodeAttribute;
	private String cleanCodeAttributeCategory;
	private List<Impact> impacts;

	// getters and setters
}

@Data
class TextRange {
	private int startLine;
	private int endLine;
	private int startOffset;
	private int endOffset;

	// getters and setters
}

@Data
class Impact {
	private String softwareQuality;
	private String severity;

	// getters and setters
}

@Data
class Component {
	private String key;
	private boolean enabled;
	private String qualifier;
	private String name;
	private String longName;
	private String path;

	// getters and setters
}
