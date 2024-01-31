export interface SkillDTO {
    skillId:number,
    skillName:string,
    skillImgUrl:string,
    skillYear:number
}

export interface SkillDto {
    skillName: string;
    skillImageUrl: string;
    skillLevel: number;
}

export interface SkillResponseDto {
    skillName: string;
    skillImageUrl: string;
    skillLevel: string;
    skillType: string;
}
