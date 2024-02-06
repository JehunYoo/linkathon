export interface SkillCategoryResponseDto {
    [category: string]: SkillResponseDto[];
}

export interface SkillResponseDto {
    skillId: number;
    skillName: string;
    skillImageUrl: string;
    skillYear:number;
}
