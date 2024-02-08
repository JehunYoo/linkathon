import {createLocalAxios} from "@/util/httpCommons.ts";

class ApiService {
    async getData(requireAuth: boolean, url: string, data: any) {
        const localAxios = createLocalAxios(requireAuth);
        return await localAxios.get(url, data);
    }

    async getDataForTeamFind(requireAuth: boolean, url: string, params?: {
        params: {
            skillIds?: number[];
            tier?: number;
            career?: number;
            gender?: boolean;
            field?: string;
            page?: number;
            size?: number
        } | undefined
    }) {
        const localAxios = createLocalAxios(requireAuth);
        return await localAxios.get(url, {params});
    }

    async postData(requireAuth: boolean, url: string, data: any) {
        const localAxios = createLocalAxios(requireAuth);
        return await localAxios.post(url, data);
    }

    async postMultipartData(requireAuth: boolean, url: string, data: FormData) {
        const localAxios = createLocalAxios(requireAuth, true);
        return await localAxios.postForm(url, data);
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