import axios from "axios";
import store from "../store";

const apiClient= axios.create({
    baseURL:"http://localhost:8080",
    timeout: 3000,
    headers:{
        "Content-Type":"application/json"
    },
    withCredentials: true
});

apiClient.interceptors.request.use(
    (config) => {
        // Content-Type 설정 (URLSearchParams인 경우)
        if (config.data && config.data instanceof URLSearchParams) {
            config.headers["Content-Type"] = "application/x-www-form-urlencoded";
        }
        const jwtToken= store.getState().userInfo.token;// (스토어에서 토큰가져오기, 컨피그에 헤더에 토큰 설정) 2가지만 제대로 추가
        config.headers['authorization']=jwtToken
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default apiClient;
// 콜백 함수 안에서는 훅(use로 시작)을 사용할 수 없다.
//csrf요청, 바디에 저자료형일


// CSRF 토큰 설정 (GET이 아닌 요청에 대해)
// if (config.method && config.method.toLowerCase() !== "get") {
//     const csrfToken = store.getState().userInfo.token;
//     config.headers["X-CSRF-TOKEN"] = csrfToken;
// }