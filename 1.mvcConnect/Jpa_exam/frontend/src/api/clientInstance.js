import axios from "axios";

const apiClient = axios.create({
    baseURL:"http://localhost:8080",
    timeout: 1000,
    headers:{
        "Content-Type": "application/json"
    }
});// (액,크, 소,중)4가지임
// (header스, 중, 따옴, 컨텐트 타입, 어제) 5가지 임.

export default apiClient;

