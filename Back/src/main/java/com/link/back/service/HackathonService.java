package com.link.back.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.link.back.config.S3Uploader;
import com.link.back.dto.Image;
import com.link.back.dto.request.HackathonRequest;
import com.link.back.dto.response.HackathonResponseDto;
import com.link.back.dto.response.HackathonsResponseDto;
import com.link.back.dto.response.ReservationResponse;
import com.link.back.dto.response.TeamResponseDto;
import com.link.back.dto.response.WinnerProjectInfoDto;
import com.link.back.dto.response.WinnerProjectResponseDto;
import com.link.back.entity.Hackathon;
import com.link.back.entity.HackathonImage;
import com.link.back.entity.Project;
import com.link.back.entity.Team;
import com.link.back.repository.HackathonImageRepository;
import com.link.back.repository.HackathonRepository;
import com.link.back.repository.HackathonsRepository;
import com.link.back.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HackathonService {
	private final HackathonRepository hackathonRepository;
	private final HackathonsRepository hackathonsRepository;
	private final HackathonImageRepository hackathonImageRepository;
	private final S3Uploader s3Uploader;
	private final ProjectRepository projectRepository;
	private final TeamBuildingService teamBuildingService;

	public void createHackathon(HackathonRequest hackathonRequest, MultipartFile image) throws IOException {
		Image img = s3Uploader.upload(image,"static");
		HackathonImage hackathonImage = HackathonImage.builder().hackathonImageUrl(img.getImageUrl()).hackathonImageName(
			img.getImageName()).hackathonOriginImageName(img.getOriginName()).build();

		Hackathon hackathon = Hackathon.builder().hackathonName(hackathonRequest.hackathonName()).startDate(hackathonRequest.startDate()).endDate(hackathonRequest.endDate())
			.teamDeadlineDate(hackathonRequest.teamDeadlineDate()).registerDate(LocalDate.now()).maxPoint(hackathonRequest.maxPoint()).maxTeamMember(hackathonRequest.max_team_member()).hackathonImage(hackathonImage).build();
		hackathonImageRepository.save(hackathonImage);
		hackathonRepository.save(hackathon);
	}

	public HackathonResponseDto getHackathonInfo(Long hackathonId) {
		//todo : HackathonImage DB에서 가져오는 로직 추가

		Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(RuntimeException::new);
		HackathonResponseDto hackathonResponseDto = HackathonResponseDto.builder().hackathonName(hackathon.getHackathonName())
			.registerDate(hackathon.getRegisterDate()).startDate(hackathon.getStartDate()).endDate(hackathon.getEndDate()).teamDeadlineDate(hackathon.getTeamDeadlineDate())
			.maxPoint(hackathon.getMaxPoint()).build();
		return hackathonResponseDto;
	}
	public Page<HackathonsResponseDto> getAllHackathonInfo(Pageable pageable, String status) {
		Page<Hackathon> hackathons = hackathonsRepository.getAllHackathons(pageable, status);

		List<HackathonsResponseDto> hackathonsResponseDtos = hackathons.getContent().stream()
			.map(this::mapToHackathonsResponseDto)
			.collect(Collectors.toList());

		return new PageImpl<>(hackathonsResponseDtos, pageable, hackathons.getTotalElements());
	}
	public void updateHackathon(Long hackathonId, HackathonRequest hackathonRequest) {
		Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(RuntimeException::new);
		hackathon.updateHackathonInfo(hackathonRequest.hackathonName(),hackathonRequest.teamDeadlineDate(),
			hackathonRequest.startDate(),hackathonRequest.endDate(),hackathonRequest.maxPoint(),hackathonRequest.max_team_member());
		hackathonRepository.save(hackathon);
	}

	public void deleteHackathon(Long hackathonId) {
		hackathonRepository.deleteById(hackathonId);
	}

	public List<WinnerProjectResponseDto> getWinnerProjects(Long hackathonId) {
		List<Project> projects = projectRepository.findProjectsByHackathonScoreAndWinState(hackathonId);

		List<WinnerProjectResponseDto> winnerProjectResponseDtoList = new ArrayList<>();
		for (Project project : projects) {
			WinnerProjectInfoDto winnerProjectInfoDto = mapToWinnerProjectInfoDto(project);
			TeamResponseDto teamResponseDto = teamBuildingService.findTeam(project.getTeam().getTeamId());
			winnerProjectResponseDtoList.add(WinnerProjectResponseDto.builder().winnerProjectInfoDto(winnerProjectInfoDto).teamResponseDto(teamResponseDto)
				.build());
		}
		return winnerProjectResponseDtoList;
	}
	private HackathonsResponseDto mapToHackathonsResponseDto(Hackathon hackathon) {
		return HackathonsResponseDto.builder()
			.hackathonId(hackathon.getHackathonId())
			.hackathonName(hackathon.getHackathonName())
			.registerDate(hackathon.getRegisterDate())
			.startDate(hackathon.getStartDate())
			.endDate(hackathon.getEndDate())
			.teamDeadlineDate(hackathon.getTeamDeadlineDate())
			.maxPoint(hackathon.getMaxPoint())
			.build();
	}

	private WinnerProjectInfoDto mapToWinnerProjectInfoDto(Project project) {
		return WinnerProjectInfoDto.builder().projectId(project.getProjectId())
			.projectName(project.getProjectName()).teamId(project.getTeam().getTeamId())
			.projectDesc(project.getProjectDesc()).build();
	}
}
