import axios from "axios";
import store from "../store";

const apiClient= axios.create({
    baseURL:"http://localhost:8080",
    timeout: 3000,
    headers:{
        "Content-Type":"application/json"
    }
});


export default apiClient;