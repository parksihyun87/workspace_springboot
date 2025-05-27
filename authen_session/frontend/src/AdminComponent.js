import {useState} from "react";
import apiClient from "./apiInstance/apiInstance";

export default function AdminComponent(){
    const[message,setMessage]=useState(null);
    const handleAdmin= async (e)=>{
        try{
            const response = await apiClient.get("/admin",{
                withCredentials:true// 쿠키를 포함해서 보내겠다고 표시

                }
                );
                setMessage(response.data);

        }catch(error){
            if(error.response && error.response.status===403){// 롤도 같이 비교해서 접근확인함
                setMessage("관리자 계정만 접근가능합니다.");
            } else {
                console.log(error);
            }
        }
    }
    return(
        <>
            <button onClick={handleAdmin}>관리자 확인</button>
            {message}
        </>
    )
}