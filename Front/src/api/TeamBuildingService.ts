import {ApiService} from "@/api/ApiService.ts";
import {TeamMemberFindDTO} from "@/dto/tmpDTOs/teamBuildingDTO.ts";
import {CatchError} from "@/util/error.ts";
// import store from "@/store";
// import {CatchError} from "@/util/error.ts";

const apiService = new ApiService();

const url = "/api"

class TeamBuildingService {
    @CatchError
    async getALlTeamFindMember(): Promise<TeamMemberFindDTO[]> {
        const response = await apiService.getData(true, `${url}/teams/recruit`, null);
        return response.data ;
    }
}

export {
    TeamBuildingService
}
