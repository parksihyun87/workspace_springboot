import axios from "axios";
const apiClient = axios.create({
    baseURL:"http://localhost:8080",
    timeout: 1000,
    header:{
        "Content-Type":"application/json"
    }
});
// axios.create(중,bu,t,h)4가지 필요함

export default apiClient;