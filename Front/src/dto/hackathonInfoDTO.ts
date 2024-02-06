interface HackathonInfoDTO {
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

interface HackathonInfoDetailDTO extends HackathonInfoDTO {
    status: string,
    count: number
}