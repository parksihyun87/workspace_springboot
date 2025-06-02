import axios from "axios";
import store from "../store";

const apiClient= axios.create({
    baseURL:"http://localhost:8080",
    timeout: 3000,
    withCredentials:true,
    headers:{
        "Content-Type":"application/json"
    }
});

apiClient.interceptors.request.use(config=>{
    if(config.data && config.data instanceof URLSearchParams){
        config.headers['Content-Type'] = "application/x-www-form-urlencoded";
    }
    const jwtToken = store.getState().token.token;
    config.headers['authorization']= jwtToken;
    return config;
}, (error)=>{
    return Promise.reject(error);
});

export default apiClient;