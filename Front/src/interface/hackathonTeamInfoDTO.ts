interface HackathonTeamInfoDTO {
    hackathonName:string,
    projectId: number,
    teamName: string,
    projectContent: string,
    nowMember: number,
    maxMember: number,
    nowPoint: number,
    maxPoint: number,
    registerDate: Date,
    imgUrl: string,
    skillList: SkillDTO[],
}