//@ts-nocheck
import {UserSkillDTO} from "@/dto/tmpDTOs/UserSkillDTO.ts";
import {UserImageDTO} from "@/dto/UserImageDTO.ts";

export interface AddUserInfoDTO {

    email: string;
    introduce: string;
    userSkills: UserSkillDTO[];
    referenceUrl: string;
    field: string;
    career: number;
    userImage: UserImageDTO;
    registered: boolean;
}