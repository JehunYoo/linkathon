import {ApiService} from "@/api/ApiService.ts";
import {httpStatusCode} from "@/util/httpStatus.ts";
import store from "@/store";
import {CatchError} from "@/util/error.ts";
import router from "@/router";
import {SendEmailRequestDTO} from "@/dto/SendEmailRequestDTO.ts";
import {FindEmailDTO} from "@/dto/FindEmailDTO.ts";
import {VerificationRequestDTO} from "@/dto/VerificationRequestDTO.ts";
import {ChangePasswordDTO} from "@/dto/ChangePasswordDTO.ts";
import {EditValidCareerDTO} from "@/dto/EditValidCareerDTO.ts";
import {UpdateUserDTO} from "@/dto/UpdateUserDTO.ts";
import {AddValidCareerDTO} from "@/dto/AddValidCareerDTO.ts";
import {AddUserInfoDTO} from "@/dto/AddUserInfoDTO.ts";
import {GetUserDataDTO} from "@/dto/GetUserDataDTO.ts";

const apiService = new ApiService();

const nonAuthUrl = "/api/users"
const authUrl = "/api/auth"

class UserService {

    @CatchError
    async logout() {
        await apiService.postData(true, `${nonAuthUrl}/logout`, '');
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
            window.location.href = "https://github.com/login/oauth/authorize/?client_id=131870e79c867e5ae6d5&redirect_uri=http://localhost:8080/oauth2/github"

        } catch (error) {
            console.error('Error during Google login:', error);
            // 에러 처리 로직 추가
        }
    }

    //로그인
    async login(user: LoginUserDTO): Promise<void> {
        try {
            const response = await apiService.postData(false, `${nonAuthUrl}/login`, user);
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
        const response = await apiService.postData(false, `${nonAuthUrl}/signup`, user);
        if (response && response.status === httpStatusCode.CREATE) {
            alert("가입 성공");
            await router.push('/')
        }
    }

    //이메일 인증인데 변경예정
    @CatchError
    async verificationEmail(email: SendEmailRequestDTO): Promise<boolean> {
        const resposne = await apiService.postData(false, `${nonAuthUrl}/signup/email`, email);

        if (resposne && resposne.status === httpStatusCode.OK) {
            alert("인증 성공");
            return true;
        } else {

            alert("다시 입력하세요");
            return false;
        }
    }

    //이메일 찾기
    async findEmail(data: FindEmailDTO): Promise<string> {

        try {
            const response = await apiService.postData(false, `${nonAuthUrl}/email`, data)

            alert(response.data)

            return response.data;

        } catch (error) {
            console.error("Error during findEmail:", error);
            return "일치하는 계정이 없습니다.";
        }
    }

    //비밀번호 찾기 -> 인증번호 요청
    async sendVerificationCode(data: SendEmailRequestDTO): Promise<boolean> {
        try {
            const response = await apiService.postData(false, `${nonAuthUrl}/email/verification`, data)

            if (response && response.status === httpStatusCode.OK) {
                alert("이메일을 확인해주세요")
                return true;
            }
            //올 일이 없을텐데 그냥 오류떠서 추가함
            else return false;
        } catch (error) {
            console.error("Error during do not Exist Email:", error);
            alert("일치하는 계정이 없습니다.")
            return false;
        }
    }

    //비밀번호 찾기 -> 인증번호 확인
    async comparedVerification(data: VerificationRequestDTO): Promise<boolean> {
        try {
            const response = await apiService.postData(false, `${nonAuthUrl}/password/verification`, data)

            if (response && response.status === httpStatusCode.OK) {
                alert("인증이 완료되었습니다.")

                return true
            } else return false;
        } catch (error) {
            console.error("Error during do not Exist Email:", error);
            alert("인증번호가 틀렸습니다.")
            return false;
        }
    }

    //비밀번호 변경
    async changePassword(data: ChangePasswordDTO) {
        try {

            const response = await apiService.postData(false, `${nonAuthUrl}/password`, data)

            if (response && response.status === httpStatusCode.OK) {
                alert("비밀번호 변경이 완료되었습니다.")
                return;
            } else {
                alert("비밀번호 변경을 실패했습니다.")
                return;
            }
        } catch (error) {
            console.error("Error during do not Exist Email:", error);
            alert("비밀번호 변경 중 문제가 발생했습니다.")
            return;
        }
    }

    //회원탈퇴
    async resignation(): Promise<boolean> {
        try {
            const response = await apiService.deleteData(true, `${authUrl}/users`, '')

            if (response && response.status === httpStatusCode.OK) {
                alert("탈퇴되었습니다.")
                return true;
            }else {return false;}
        }
        catch (error) {
            alert("작업 중 문제가 발생했습니다.")
            return false;
        }
    }

    //경력인증 API
    async validCareer(data: EditValidCareerDTO): Promise<number> {

        try {
            const response = await apiService.postData(true, `${nonAuthUrl}/career`, data)
            const responseData = response.data;

            if (response && response.status === httpStatusCode.OK) {
                alert("경력인증이 완료되었습니다.")
                return responseData;
            }else {return -1;}
        }
        catch (error) {
            alert("작업 중 문제가 발생했습니다.")
            return -1;
        }
    }

    async addValidCareer(data: AddValidCareerDTO): Promise<number> {

        try {
            const response = await apiService.postData(true, `${nonAuthUrl}/career`, data)
            const responseData = response.data;

            if (response && response.status === httpStatusCode.OK) {
                alert("경력인증이 완료되었습니다.")
                return responseData;
            }else {return -1;}
        }
        catch (error) {
            alert("작업 중 문제가 발생했습니다.")
            return -1;
        }
    }
    async updateUser(data: UpdateUserDTO): Promise<boolean> {

        try {
            const response = await apiService.putData(true, `${authUrl}/users`, data)
            if (response && response.status === httpStatusCode.OK) {
                alert("정보가 수정되었습니다.")
                return true;
            }
            else {
                alert("작업중 문제가 발생했습니다.")
                return false;
            }
        }
        catch (error) {
            console.log(error)
            alert("작업중 문제가 발생했습니다.")
            return false;
        }
    }

    async addUserInfo(data: AddUserInfoDTO) {
        try {
            const response = await apiService.postData(true, `${authUrl}/users/addtionalinfo`, data)
            if (response && response.status === httpStatusCode.OK) {
                alert("추가 정보가 등록되었습니다.")
                return;
            }
            else {
                alert("작업중 문제가 발생했습니다.")
                return;
            }
        }
        catch (error) {
            console.log(error)
            alert("작업중 문제가 발생했습니다.")
            return;
        }

    }

    async getUserData() {
        try {
            const response = await apiService.getData(true, `${authUrl}/users`);
            if (response && response.status === httpStatusCode.OK) {
                alert("추가 정보가 등록되었습니다.")
                return response.data as Promise<GetUserDataDTO>;
            }
            else {
                alert("작업중 문제가 발생했습니다.")
            }
        }
        catch (error){
            console.log(error)
            alert("작업중 문제가 발생했습니다.")
        }
    }

}

export {
    UserService
}