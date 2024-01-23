import {createLocalAxios} from "@/util/httpCommons.ts";

class ApiService {
    async getData(requireAuth: boolean, url: string, data: any) {
        const localAxios = createLocalAxios(requireAuth);
        return await localAxios.get(url, data);
    }

    async postData(requireAuth: boolean, url: string, data: any) {
        const localAxios = createLocalAxios(requireAuth);
        return await localAxios.post(url, data);
    }

    async deleteData(requireAuth: boolean, url: string, data: any) {
        const localAxios = createLocalAxios(requireAuth);
        return await localAxios.delete(url, data);
    }

    async putData(requireAuth: boolean, url: string, data: any) {
        const localAxios = createLocalAxios(requireAuth);
        return await localAxios.put(url, data);
    }
}

export {
    ApiService,
}