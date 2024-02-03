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

    //oAuth2 Google 로그인 요청
    async googleLogin() {

        try {
            // 추출한 URL로 리다이렉트 수행
            window.location.href = "http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:8080/login/oauth2/code/google"

        } catch (error) {
            console.error('Error during Google login:', error);
            // 에러 처리 로직 추가
        }
    }
    async githubLogin() {

        try {
            // 추출한 URL로 리다이렉트 수행
            window.location.href = "http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:8080/login/oauth2/code/google"

        } catch (error) {
            console.error('Error during Google login:', error);
            // 에러 처리 로직 추가
        }
    }

    //로그인
    async login(user: LoginUserDTO): Promise<void> {
        try {
            const response = await apiService.postData(false, `${url}/login`, user);
            if (response && response.status === httpStatusCode.OK) {
                const authToken = response.headers['authorization'];
                if (authToken) {
                    await store.dispatch("updateToken", authToken);
                    alert("로그인 성공");
                    await router.push('/')
                } else {
                    alert("로그인 실패");
                }
            }
        } catch (error) {
            alert("로그인 실패");
        }
    }

    //회원가입
    @CatchError
    async sign(user: UserSignUpDto): Promise<void> {
        const response = await apiService.postData(false, `${url}/signup`, user);
        if (response && response.status === httpStatusCode.OK) {
            alert("가입 성공");
            await router.push('/')
        }
    }
}

export {
    UserService
}