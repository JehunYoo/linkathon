import {Image, ProjectStatus} from "@/dto/tmpDTOs/commonDTO.ts";

export interface ProjectDTO {
    projectId: number,
    projectName: string,
    projectDesc: string,
    starCount: number,
    starred: boolean,
    imgSrc: string,
}

export interface ProjectInfoDetailDTO extends ProjectDTO {
    status: string,
    count: number
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


export interface ProjectDetailResponseDto {
    projectId: number;
    teamId: number;
    hackathonId: number;
    hackathonName: string;
    hackathonTopic: string;
    hackathonScore: number;
    projectName: string;
    projectDesc: string;
    projectStatus: ProjectStatus;
    registeredDate: Date;
    projectUrl: string;
    deployUrl: string;
    winState: boolean;
    imgSrc: string;
    starCount: number;
    starred: boolean;
}

export interface ProjectResponseDto {
    projectId: number;
    projectName: string;
    projectDesc: string;
    starCount: number;
    starred: boolean;
    image: Image;
}
