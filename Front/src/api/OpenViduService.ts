import {ApiService} from "@/api/ApiService.ts";
import {CatchError} from "@/util/error.ts";

const url = "/api";

class OpenViduService {

    apiService: ApiService;

    constructor() {
        this.apiService = new ApiService();
    }

    @CatchError
    async getToken(mySessionId: string) {
        const sessionId = await this.createSession(mySessionId);
        return await this.createToken(sessionId);
    }

    @CatchError
    async createSession(sessionId: string) {
        const response = await this.apiService.postData(true, url + '/sessions', { customSessionId: sessionId });
        return response.data; // The sessionId
    }

    @CatchError
    async createToken(sessionId: string) {
        const response = await this.apiService.postData(true, url + '/sessions/' + sessionId + '/connections', {});
        return response.data; // The token
    }

}

export {OpenViduService}
