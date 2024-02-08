//@ts-nocheck
import {UserSkillDTO} from "@/dto/tmpDTOs/UserSkillDTO.ts";
import {UserImageDTO} from "@/dto/UserImageDTO.ts";

export interface AddUserInfoDTO {

    introduce: string;
    userSkills: UserSkillDTO[];
    referenceUrl: string;
    field: string;
    career: number;
    userImage: UserImageDTO;
    registered: boolean;
}