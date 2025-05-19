import axios from "axios";//
// 클라이언트 인스탄스
const apiClient = axios.create({
    //  ac=a.c(axios, . , create 3가지)
    baseURL:"http://localhost:8080",//베유: 주소
    timeout: 1000,//타아: 타임
    headers:{//헤더스: 콘타: 어플제슨
        "Content-Type":"application/json"
    }
});
// 액크=액.크,( (베유, 타아, 헤더스) 3가지)


export default apiClient;
//익포 디포 앱클