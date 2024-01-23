import {ApiService} from "@/api/ApiService.ts";
import {httpStatusCode} from "@/util/httpStatus.ts";
import store from "@/store";
import {CatchError} from "@/util/error.ts";

const apiService = new ApiService();

const url = "/api/v1"

class UserService {
    async login(user: User): Promise<void> {
        try {
            const response = await apiService.postData(false, `${url}/auth/login`, user);
            if (response && response.status === httpStatusCode.OK) {
                await store.dispatch("updateToken", response.data);
                alert("로그인 성공");
            }
        } catch (error) {
            alert("로그인 실패");
        }
    }

    @CatchError
    async sign(user: User): Promise<void> {
        const response = await apiService.postData(false, `${url}/users`, user);
        if (response && response.status === httpStatusCode.OK) {
            alert("가입 성공");
        }
    }
}

export {
    UserService
}