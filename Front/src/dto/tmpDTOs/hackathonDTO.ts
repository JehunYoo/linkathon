import {TeamResponseDto} from "@/dto/tmpDTOs/teamDTO.ts";

export interface HackathonDTO {
    hackathonId: number,
    title:string[],
    recruitmentStart:Date,
    recruitmentEnd:Date,
    flowStart:Date,
    flowEnd:Date,
    announce:Date,
    subject:string,
    imgSrc:string,
}

export interface HackathonInfoDetailDTO extends HackathonDTO {
    status: string,
    count: number
}

export interface HackathonRequest {
    hackathonName: string;
    teamDeadlineDate: Date;
    startDate: Date;
    endDate: Date;
    maxPoint: number;
    max_team_member: number;
}

export interface HackathonInfoResponseDto {
    hackathonName: string;
    registerDate: Date;
    teamDeadlineDate: Date;
    startDate: Date;
    endDate: Date;
    hackathonImageName: string;
    hackathonOriginImageName: string;
}

export interface HackathonResponseDto {
    hackathonName: string;
    registerDate: Date;
    teamDeadlineDate: Date;
    startDate: Date;
    endDate: Date;
    maxPoint: number;
    hackathonImageName: string;
    hackathonImageUrl: string;
    hackathonOriginImageName: string;
}

export interface HackathonsResponseDto {
    hackathonId: number;
    hackathonName: string;
    registerDate: Date;
    teamDeadlineDate: Date;
    startDate: Date;
    endDate: Date;
    maxPoint: number;
    hackathonImageName: string;
    hackathonImageUrl: string;
    hackathonOriginImageName: string;
}

export interface WinnerProjectInfoDto {
    projectId: number;
    teamId: number;
    projectName: string;
    projectDesc: string;
}

export interface WinnerProjectResponseDto {
    winnerProjectInfoDto: WinnerProjectInfoDto;
    teamResponseDto: TeamResponseDto;
}
