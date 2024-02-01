import {ProjectStatus} from "@/dto/tmpDTOs/commonDTO.ts";

export interface ProjectInfoDTO {
    projectId: number,
    projectName: string,
    projectDesc: string,
    starCount: number,
    starred: boolean,
    imgSrc: string,
}

export interface ProjectDetailDto extends ProjectInfoDTO{
    // projectId: number;
    teamId: number;
    hackathonId: number;
    hackathonName: string;
    hackathonTopic: string;
    hackathonScore: number;
    // projectName: string;
    // projectDesc: string;
    projectStatus: ProjectStatus;
    registeredDate: Date;
    projectUrl: string;
    deployUrl: string;
    winState: boolean;
    // imgSrc: string;
    // starCount: number;
    // starred: boolean;
}

export interface ProjectRequestDto {
    teamId: number;
    projectName: string;
    projectDesc: string;
    projectUrl: string;
    deployUrl: string;
}

export interface BackPerformanceResponseDto {
    backPerformanceId: number;
    bugs: number;
    codeSmells: number;
    coverage: number;
    duplications: number;
    securityRating: number;
    vulnerabilities: number;
}
