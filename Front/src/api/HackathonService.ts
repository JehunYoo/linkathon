import {ApiService} from "@/api/ApiService.ts";
import {httpStatusCode} from "@/util/httpStatus.ts";
import {WinnerProjectResponseDto} from "@/dto/tmpDTOs/hackathonDTO.ts";
const apiService = new ApiService();

const url = "/api"

class HackathonService {
    async getHackathonList(hackathonStatus : string, page :number, size:number): Promise<HackathonInfoDTO[]> {
        try {
            const response = await apiService.getData(true, `${url}/hackathons`,{
                params : {
                    page: page,
                    size: size,
                    status: hackathonStatus
                }
            });
            if (response && response.status === httpStatusCode.OK) {

                console.log(response.data)
                return response.data.content as HackathonInfoDTO[];
            }
        } catch (error) {
            console.log(error);

        }
        return [];
    }

    async getHackathonDetail(hackathonId : number) : Promise<HackathonInfoDTO> {
        try {
            const response = await apiService.getData(true, `${url}/hackathons/${hackathonId}`,'');
            if (response && response.status === httpStatusCode.OK) {
                console.log(response.data)
                return response.data as HackathonInfoDTO;
            }
        } catch (error) {
            console.log(error);

        }
        return {} as HackathonInfoDTO;
    }

    async getWinnerProjects(hackathonId : number) : Promise<WinnerProjectResponseDto[]> {
        try {
            const response = await apiService.getData(true, `${url}/hackathons/${hackathonId}/winners`, '')
            if (response && response.status === httpStatusCode.OK) {
                console.log(response.data)
                return response.data as WinnerProjectResponseDto[];
            }
        } catch (error) {
            console.log(error);

        }
        return [] as WinnerProjectResponseDto[];
    }
}

export {HackathonService};