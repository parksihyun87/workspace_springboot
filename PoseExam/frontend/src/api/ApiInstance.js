import axios from "axios";
import {setToken, store} from "../store";


const apiClient = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 3000,
    headers:{
        "Content-Type":"application/json"
    },
    withCredentials: true
});

apiClient.interceptors.request.use(
    (config)=>{
        if(config.data && config.data instanceof URLSearchParams){
            config.headers["Content-Type"]= "application/x-www-form-urlencoded";
        }
        const jwtToken = store.getState().post.token;
        config.headers['authorization']=jwtToken // access 토큰
        return config;
    },
    (error)=>{
        return Promise.reject(error);
    }
);

apiClient.interceptors.response.use((response)=>response,
    async (error)=>{
        const originalRequest= error.config;// 조건을 만들어서 진입 확인
        if(error.response && error.response.status===456 && ! originalRequest._retry) {
            originalRequest._retry = true;
            // 연결되는 요는, 만료로 인해 오류가 나서 인터셉트에서 간섭해서 그 경우의 응답만 바꾸기로 함
            try {
                const response = await axios.post("http://localhost:8080/reissue", null,
                    {
                        withCredentials: true
                    }
                );
                const access = response.headers['authorization'];
                store.dispatch(setToken(access));
                console.log("만료된 요청 재시도");
                return apiClient(originalRequest);
            } catch (error) {
                console.log("리프레시 토큰을 통해 재발급 실패", error);
                return Promise.reject(error);
            }
        }
        return Promise.reject(error);
    }
)

export default apiClient;
