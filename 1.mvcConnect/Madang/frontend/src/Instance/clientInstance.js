import axios from "axios";

const apiClient= axios.create({
    baseURL:"http://localhost:8080",
    timeout: 1000,
    headers: {
        "Content-Type":"application/json"
    }
}
);

export default apiClient;
// 액,중 2가지 , bth 3가지 //펑션부로 함수 컴퍼넌트 없고 const만 있음.