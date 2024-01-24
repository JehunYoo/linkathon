interface SkillCategorySelect {
    selectedCategory: number,
    selectSkillId: Set<number>,
    skillCategoryList: SkillCategory[]
}

interface MemberRecruit extends SkillCategorySelect {
    tier:number,
    year:number,
    gender:boolean,
    category:string
}