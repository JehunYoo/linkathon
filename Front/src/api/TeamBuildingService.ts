import {ApiService} from "@/api/ApiService.ts";
import {TeamMemberFindDTO} from "@/dto/tmpDTOs/teamBuildingDTO.ts";
import {CatchError} from "@/util/error.ts";
import {SkillCategoryResponseDto} from "@/dto/tmpDTOs/SkillTypeDTO.ts";

const apiService = new ApiService();

const url = "/api"

class TeamBuildingService {
    // @CatchError
    // async getAllTeamFindMember(): Promise<TeamMemberFindDTO> {
    //     const response = await apiService.getData(false, `${url}/teams/recruit`);
    //     return response.data;
    // }

    @CatchError
    async getAllTeamFindMember(params?: {
        skillIds?: number[];
        tier?: number;
        career?: number;
        gender?: boolean;
        field?: string;
        page?: number;
        size?: number
    }): Promise<TeamMemberFindDTO> {
        const formattedParams = Object.entries(params || {})
            .map(([key, value]) => Array.isArray(value) ? value.map(val => `${key}=${encodeURIComponent(val)}`).join('&') : `${key}=${encodeURIComponent(value)}`)
            .join('&');
        const urlWithParams = `${url}/teams/recruit${formattedParams ? `?${formattedParams}` : ''}`;
        return (await apiService.getData(false, urlWithParams)).data as TeamMemberFindDTO;
    }


    @CatchError
    async getAllSkillType(): Promise<SkillCategoryResponseDto> {
        const response = await apiService.getData(false, `${url}/skill`);
        return response.data;
    }

}

export {
    TeamBuildingService
}
