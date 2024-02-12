import {ApiService} from "@/api/ApiService.ts";
import {CatchError} from "@/util/error.ts";
import {MypageConditionDTO} from "@/dto/tmpDTOs/MypageConditionDTO.ts";

const apiService = new ApiService();

const url = "/api"

class MyPageService {

    @CatchError
    async getMyPageCond(): Promise<MypageConditionDTO> {
        const response = await apiService.getData(true, `${url}/teams/mypage/condition`);
        return response.data;
    }

}

export {
    MyPageService
}
