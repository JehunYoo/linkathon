import {ApiService} from "@/api/ApiService.ts";
import {httpStatusCode} from "@/util/httpStatus.ts";

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
                alert("조회 성공");
                console.log(response.data)
                return response.data.content as HackathonInfoDTO[];
            }
        } catch (error) {
            console.log(error);
            alert("조회 실패");
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
            alert("조회 실패");
        }
        return {} as HackathonInfoDTO;
    }
}

export {HackathonService};