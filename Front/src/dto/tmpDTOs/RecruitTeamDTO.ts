import {TeamMemberFindUserDTO} from "@/dto/tmpDTOs/teamBuildingDTO.ts";

export interface RecruitTeamDTO {
    teamName:string,
    teamDesc:string,
    members:{ [key: string]: TeamMemberFindUserDTO[] };
}
