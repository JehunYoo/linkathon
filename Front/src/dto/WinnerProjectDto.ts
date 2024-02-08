import {TeamResponseDto} from "@/dto/tmpDTOs/teamDTO.ts"
interface WinnerProjectDto {
    projectId : number,
    teamId : number,
    projectName : String,
    projectDesc : String,
    teamName: string;
    teamMember: number;
    teamDesc: string;
    teamResponse : TeamResponseDto
}