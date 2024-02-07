import {ApiService} from "@/api/ApiService.ts";
import {httpStatusCode} from "@/util/httpStatus.ts";
import {WinnerProjectResponseDto} from "@/dto/tmpDTOs/hackathonDTO.ts";
import {
    HackathonProceedingProjectResponseDto,
    PageableProceedingHackathons
} from "@/dto/tmpDTOs/hackathonProceedingProjectResponseDto.ts";
import {AxiosResponse} from "axios";
import {Builder} from "builder-pattern";
import {HackathonInfoDTO, PageableHackathonList} from "@/dto/hackathonInfoDTO.ts";
const apiService = new ApiService();

const url = "/api"
interface pageableData {
    content: Object,
    pageable: {
        pageNumber: number,
    }
    totalPages: number,
}
class HackathonService {
    async getHackathonList(hackathonStatus : string, page :number, size:number): Promise<PageableHackathonList> {
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
                return this.toPageableHackathonsList(response) as PageableHackathonList;
            }
        } catch (error) {
            console.log(error);

        }
        return {} as PageableHackathonList;
    }

    async getHackathonDetail(hackathonId : number) : Promise<HackathonInfoDTO> {
        try {
            const response = await apiService.getData(true, `${url}/hackathons/${hackathonId}`, '');
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

    async getProceedingLeaderboards(hackathonId : number) : Promise<PageableProceedingHackathons> {
        try {
            const response = await apiService.getData(true, `${url}/hackathons/${hackathonId}/proceeding`, '')
            if (response && response.status === httpStatusCode.OK) {
                console.log("r",response.data)
                return this.toPageableHackathons(response) as PageableProceedingHackathons;
            }
        } catch (error) {
            console.log(error);

        }
        return {} as PageableProceedingHackathons;
    }
    toPageableHackathons = (response: AxiosResponse<any, any>): PageableProceedingHackathons => {
        const data = response.data as pageableData;
        return Builder<PageableProceedingHackathons>()
            .hackathons(data.content as HackathonProceedingProjectResponseDto[])
            .pageable(Builder<PageableDto>()
                .pageNumber(data.pageable.pageNumber)
                .totalPages(data.totalPages)
                .build())
            .build();
    }
    toPageableHackathonsList = (response: AxiosResponse<any, any>): PageableHackathonList => {
        const data = response.data as pageableData;
        return Builder<PageableHackathonList>()
            .hackathons(data.content as HackathonInfoDTO[])
            .pageable(Builder<PageableDto>()
                .pageNumber(data.pageable.pageNumber)
                .totalPages(data.totalPages)
                .build())
            .build();
        return {} as PageableHackathonList;
    }
    }

export {HackathonService};