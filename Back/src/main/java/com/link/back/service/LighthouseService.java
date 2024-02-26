package com.link.back.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.link.back.dto.LighthouseReportResponseDTO;
import com.link.back.dto.response.LighthouseResponseDTO;
import com.link.back.entity.LighthouseReport;
import com.link.back.entity.LighthouseUri;
import com.link.back.entity.Project;
import com.link.back.repository.LightHouseReportRepository;
import com.link.back.repository.LightHouseUriRepository;
import com.link.back.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LighthouseService {
	private final ProjectRepository projectRepository;
	private final LightHouseUriRepository lightHouseUriRepository;
	private final LightHouseReportRepository lightHouseReportRepository;

	private String getUrl(Long projectId) {
		return projectRepository.findById(projectId).orElseThrow().getDeployUrl();
	}

	private List<LighthouseUri> getUriList(Long projectId) {
		return lightHouseUriRepository.findByProject_ProjectId(projectId);
	}

	public List<String> getUrlUrlList(Long projectId) {
		String url = getUrl(projectId);
		List<LighthouseUri> data = getUriList(projectId);
		return data.stream()
			.map(lighthouseUri -> url + lighthouseUri.getUri())
			.toList();
	}

	public List<LighthouseResponseDTO> getReportList(Long projectId) {
		return lightHouseReportRepository.getAllByProject_ProjectId(projectId)
			.stream()
			.map((data) ->
				LighthouseResponseDTO.builder()
					.url(data.getUrl())
					.report(data.getReport()).build())
			.toList();
	}

	@Transactional
	public void saveUri(Long projectId, List<String> list) {
		List<LighthouseUri> lighthouseUriList = list.stream().map((data) ->
			LighthouseUri.builder()
				.project(Project.builder().projectId(projectId).build())
				.uri(data).build())
			.toList();
		lightHouseUriRepository.saveAll(lighthouseUriList);
	}

	private void cleanUri(Long projectId) {
		lightHouseUriRepository.removeByProject_ProjectId(projectId);
	}

	@Transactional
	public void saveReport(LighthouseReportResponseDTO report) {
		cleanReport(report.getProjectId());
		List<LighthouseReport> lighthouseReportList = report.getData().stream()
			.map(data -> LighthouseReport.builder()
				.project(Project.builder().projectId(report.getProjectId()).build())
				.url(data.getUrl())
				.report(data.getDetail())
				.build())
			.toList();
		lightHouseReportRepository.saveAll(lighthouseReportList);
	}

	private void cleanReport(Long projectId) {
		lightHouseReportRepository.removeAllByProject_ProjectId(projectId);
	}
}
