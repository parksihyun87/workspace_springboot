import axios from "axios";

const apiClient = axios.create({
    baseURL:"http://localhost:8080",
    timeout:5000,
    headers:{
        "Content-Type":"application/json",
    }
});

apiClient.interceptors.request.use((config)=>{
    config.headers["Custom-header"]="hello";
    console.log("요청:", Date.now());
    return config;
},(error)=>{
    return Promise.reject(error);
});

export default apiClient;



