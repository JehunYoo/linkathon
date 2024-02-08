
export interface HackathonInfoDTO {
    hackathonId: number,
    hackathonName:string[],
    registerDate:Date,
    teamDeadlineDate:Date,
    startDate:Date,
    endDate:Date,
    // announce:Date,
    hackathonTopic:string,
    hackathonImageUrl:string,
    maxTeamMember:number,
    maxPoint : number
}
export interface PageableHackathonList {
    hackathons: HackathonInfoDTO[];
    pageable: PageableDto;
}