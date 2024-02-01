import axios, {AxiosError, AxiosInstance, AxiosResponse} from "axios";
import store from "@/store";

const {VITE_VUE_API_URL} = import.meta.env;

function createLocalAxios(requireAuth: boolean): AxiosInstance {
    const local: any = axios.create({
        baseURL: VITE_VUE_API_URL,
        withCredentials: true,
        headers: {
            "Content-Type": "application/json;charset=utf-8",
        },
    });
    if (requireAuth) {
        authorization(local);
    }
    tokenInterceptor(local);
    return local;
}

function authorization(axiosInstance: any) {
    // const token = localStorage.getItem("accessToken");
    const token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNzA2NTE0NjIyfQ.RqrwRJ8AuxTnR84MpvOD6Ix9dFU9iH_YD77vdFv5zsE';
    if (token) {
        axiosInstance.defaults.headers['Authorization'] = `${token}`;
    } else {
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
