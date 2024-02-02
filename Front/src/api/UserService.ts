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

    async googleLogin() {
        try {
            // 백엔드에 요청을 보내고 응답을 받아옴
            const response = await apiService.getData(false, `/oauth2/authorization/google`, '');

            // 응답에서 리다이렉트 URL 직접 추출
            const redirectUrl = response.data; // 이 부분은 응답에 맞게 수정

            console.log(redirectUrl)

            // 추출한 URL로 리다이렉트 수행
            window.location.href = redirectUrl;
        } catch (error) {
            console.error('Error during Google login:', error);
            // 에러 처리 로직 추가
        }
    }


    async login(user: LoginUserDTO): Promise<void> {
        try {
            const response = await apiService.postData(false, `${url}/login`, user);
            if (response && response.status === httpStatusCode.OK) {
                await store.dispatch("updateToken", response.headers['authorization']);
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