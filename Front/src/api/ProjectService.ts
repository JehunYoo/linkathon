import {ApiService} from "@/api/ApiService.ts";
import {httpStatusCode} from "@/util/httpStatus.ts";
// import store from "@/store";
// import {CatchError} from "@/util/error.ts";

const apiService = new ApiService();

const url = "/api"

class ProjectService {
    async getALlClosedProjects() : Promise<void | ProjectInfoDTO[]> {
        try {
            const response = await apiService.getData(true, `${url}/projects`, null);
            if (response && response.status === httpStatusCode.OK) {
                alert("조회 성공");
                return response.data as ProjectInfoDTO[];
            }
        } catch (error) {
            alert("조회 실패");
        }
    }

    async getProjectContributions(owner : String, repo : String) : Promise<GitStatusDTO[]> {
        try {
            const response = await apiService.getData(true, `${url}/projects/contributions/${owner}/${repo}`, null);
            if (response && response.status === httpStatusCode.OK) {
                alert("조회 성공");
                return response.data as GitStatusDTO[];
            }
        } catch (error) {
            alert("조회 실패");
        }
        return [] as GitStatusDTO[];
    }

    // async login(user: UserDTO): Promise<void> {
    //     try {
    //         const response = await apiService.postData(false, `${url}/auth/login`, user);
    //         if (response && response.status === httpStatusCode.OK) {
    //             await store.dispatch("updateToken", response.data);
    //             alert("로그인 성공");
    //         }
    //     } catch (error) {
    //         alert("로그인 실패");
    //     }
    // }
    //
    // @CatchError
    // async sign(user: UserDTO): Promise<void> {
    //     const response = await apiService.postData(false, `${url}/users`, user);
    //     if (response && response.status === httpStatusCode.OK) {
    //         alert("가입 성공");
    //     }
    // }
}

export {
    ProjectService
}