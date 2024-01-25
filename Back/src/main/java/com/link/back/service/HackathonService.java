package com.link.back.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.link.back.dto.request.HackathonRequest;
import com.link.back.dto.response.HackathonResponseDto;
import com.link.back.dto.response.HackathonsResponseDto;
import com.link.back.dto.response.ReservationResponse;
import com.link.back.entity.Hackathon;
import com.link.back.entity.HackathonImage;
import com.link.back.repository.HackathonImageRepository;
import com.link.back.repository.HackathonRepository;
import com.link.back.repository.HackathonsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HackathonService {
	private final HackathonRepository hackathonRepository;
	private final HackathonsRepository hackathonsRepository;
	private final HackathonImageRepository hackathonImageRepository;

	public void createHackathon(HackathonRequest hackathonRequest) {
		Hackathon hackathon = Hackathon.builder().hackathonName(hackathonRequest.hackathonName()).startDate(hackathonRequest.startDate()).endDate(hackathonRequest.endDate())
			.teamDeadlineDate(hackathonRequest.teamDeadlineDate()).registerDate(LocalDate.now()).maxPoint(hackathonRequest.maxPoint()).build();
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
			hackathonRequest.startDate(),hackathonRequest.endDate(),hackathonRequest.maxPoint());
		hackathonRepository.save(hackathon);
	}

	public void deleteHackathon(Long hackathonId) {
		hackathonRepository.deleteById(hackathonId);
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
}
