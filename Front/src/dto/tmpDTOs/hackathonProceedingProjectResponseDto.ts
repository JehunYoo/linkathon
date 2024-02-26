export interface HackathonProceedingProjectResponseDto {
    teamId : number,
    projectId : number,
    teamName : string,
    teamMembers : string[],
    hackathonScore : number
}

export interface PageableProceedingHackathons {
    hackathons: HackathonProceedingProjectResponseDto[];
    pageable: PageableDto;
}