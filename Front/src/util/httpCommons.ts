import axios, {AxiosInstance} from "axios";

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
    return local;
}

function authorization(axiosInstance: any) {
    const token = localStorage.getItem("accessToken");
    if (token) {
        axiosInstance.defaults.headers['Authorization'] = `Bearer ${token}`;
    } else {
        throw new Error('Authorization token is missing');
    }
}

export {createLocalAxios};
