import axios from "axios";
import {store,setToken} from "../store";

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
// 만료된 응답이 나오면 리이슈시키는 인터셉터
apiClient.interceptors.response.use((response)=>response,
    async (error)=>{
        const originalRequest = error.config;
        if(error.response && error.response.status===456 && !originalRequest._retry){
            originalRequest._retry=true;
            try {
                const response = await axios.post("http://localhost:8080/reissue",null,
                    {
                        withCredentials:true
                    });
                // 이제 응답객체의 헤더에 어서라이제이션 억세스 토큰이 들어있다.
                const access = response.headers['authorization'];
                store.dispatch(setToken(access));
                console.log("만료된 요청 재시도");
                return apiClient(originalRequest);
            } catch (error) {
                console.log("리프레시 토큰으로 재발급 실패", error);
                return Promise.reject(error);// 오류1
            }
        }
        return Promise.reject(error);//if문 못들어간 오류
    }
)



export default apiClient;
// 콜백 함수 안에서는 훅(use로 시작)을 사용할 수 없다.
//csrf요청, 바디에 저자료형일


// CSRF 토큰 설정 (GET이 아닌 요청에 대해)
// if (config.method && config.method.toLowerCase() !== "get") {
//     const csrfToken = store.getState().userInfo.token;
//     config.headers["X-CSRF-TOKEN"] = csrfToken;
// }