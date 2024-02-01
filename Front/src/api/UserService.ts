import {ApiService} from "@/api/ApiService.ts";
import {httpStatusCode} from "@/util/httpStatus.ts";
import store from "@/store";
import {CatchError} from "@/util/error.ts";
import router from "@/router";

const apiService = new ApiService();

const url = "/api/users"

class UserService {

    @CatchError
    async logout() {
        await apiService.postData(true, `${url}/logout`, '');
        await store.dispatch('removeToken');
    }

    async login(user: LoginUserDTO): Promise<void> {
        try {
            const response = await apiService.postData(false, `${url}/login`, user);
            if (response && response.status === httpStatusCode.OK) {
                alert("로그인 성공");
                await router.push('/')
            }
        } catch (error) {
            alert("로그인 실패");
        }
    }

    @CatchError
    async sign(user: UserSignUpDto): Promise<void> {
        const response = await apiService.postData(false, `${url}/signup`, user);
        if (response && response.status === httpStatusCode.OK) {
            alert("가입 성공");
        }
    }
}

export {
    UserService
}