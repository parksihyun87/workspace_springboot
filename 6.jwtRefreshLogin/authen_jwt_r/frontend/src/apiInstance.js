import axios from "axios";
import store, {setToken} from "./store";

const apiClient = axios.create({
    baseURL:"http://localhost:8080",
    headers:{
        "Content-Type" : "application/json",
    },
    timeout:3000,
    withCredentials:true,
    });

apiClient.interceptors.request.use(config=>{
    if(config.data && config.data instanceof URLSearchParams){
        config.headers['Content-Type'] = "application/x-www-form-urlencoded";
    }
    // 이런식으로 하다가, 만료되면 재발급을 받아야 함.
    const jwtToken = store.getState().token.token;
    config.headers['authorization']=jwtToken;
    return config;
}, (error)=>{
    return Promise.reject(error);
});


apiClient.interceptors.response.use((response)=>response,//응답객체 오류 없으면 그냥 보냄, 모든 리스폰스에 대해서 실행함.
    async (error)=>{
    const originalRequest= error.config;// 에러에 컨피그는, 이 응답을 보내게 만든 요청객체를 가져와서 리퀘스트에 넣어놓음. 오리지날 리퀘스트는 요청인데, 실패한 요청
        // 서버에 가보지도 못한 오류는 request 오류로 리스폰스도 없지만, 일단 갔다오면 리스폰스는 있음, 보통 토큰 만료되면 401이 반환이 됨.
        //오리지날 리퀘스트는 요청객체인데, 원래는 없는 멤버인데 넣어줄 것임. 자바스크립트에는 속성 추가가 항상 됨.
        if(error.response && error.response.status === 456 && !originalRequest._retry){
            originalRequest._retry= true;// 특정요청에 대하여 오류요청을 한번만 하겠다. 리트라이라는 속성이 없어서 언디파인이다가 생겨서 위를 못들어가게 함.
            try{
                const response = await axios.post("http://localhost:8080/reissue", null, {
                    withCredentials:true,
                });
                const access = response.headers['authorization'];// 이제 리덕스에 저장해야 함,
                store.dispatch(setToken(access));// 스토어 안의 디스패치라는 메서드가 있는데 이걸 통해서 접근함
                console.log("만료된 요청 재시도")
                // 한번 실패해서 오류 난것이니 다시 시도해서 승인 얻어야 함
                return apiClient(originalRequest);// 리턴 이렇게 하면 다시 위로 가서 다시 실행함.
            }catch (error) {
                console.log("리프레시 토큰으로 재발급 실패", error);
                return Promise.reject(error);//여기서도 예외처리는 해줘야 함, 트라이 하다가 발생한 에러. 본청으로 넘김(핸들)
            }
        }
        return Promise.reject(error);// 프로미스.리젝트는 약속이 완료되었을때의 결과다 그래서 에러는 실패했을때 보냄. 맨위, 얘도 에러를 본청에 보냄
    }
)// 응답이 리턴되기 전에 가로채는 역할인데, 토큰이 만료이면 오류 응답이 됨. 앞에는 인터셉트로 실행할 함수, 뒤쪽에는 오류가 났을때 실행함수

export default apiClient;