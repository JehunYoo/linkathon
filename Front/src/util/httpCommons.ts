import axios, {AxiosError, AxiosInstance, AxiosResponse} from "axios";
import store from "@/store";

const {VITE_VUE_API_URL, VITE_VUE_API_LIVE_URL} = import.meta.env;

function createLocalAxios(requireAuth: boolean, isFormData?: boolean): AxiosInstance {
    const {DEV} = import.meta.env;
    let URL = "";
    if (DEV) URL = VITE_VUE_API_URL;
    else URL = VITE_VUE_API_LIVE_URL

    const local: any = axios.create({
        baseURL: URL,
        withCredentials: true,
        headers: {
            "Content-Type": !isFormData ? "application/json;charset=utf-8" : 'multipart/form-data',
        },
    });
    if (requireAuth) {
        authorization(local);
        tokenInterceptor(local);
    }
    return local;
}

function authorization(axiosInstance: any) {
    const token = store.getters.getToken;
    if(token) {
        if(token.substring(0,6) !== 'Bearer') {
            axiosInstance.defaults.headers['Authorization'] = `Bearer ${token}`;
        } else {
            axiosInstance.defaults.headers['Authorization'] = `${token}`;
        }
    }
    else {
        throw new Error('Authorization token is missing');
    }
}

function tokenInterceptor(axiosInstance: any) {
    axiosInstance.interceptors.response.use(
        async (response: AxiosResponse) => {
            const authToken = response.headers['authorization'];
            if (authToken) {
                await store.dispatch("updateToken", authToken);
            }
            return response;
        },
        (error: AxiosError) => {
            return Promise.reject(error);
        }
    );
}
export {createLocalAxios};
