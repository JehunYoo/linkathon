interface HackathonInfoDTO {
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

interface HackathonInfoDetailDTO extends HackathonInfoDTO {
    status: string,
    count: number
}