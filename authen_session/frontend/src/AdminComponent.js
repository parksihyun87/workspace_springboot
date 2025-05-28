import {useState} from "react";
import apiClient from "./apiInstance/apiInstance";
import axios from "axios";
import {useSelector} from "react-redux";

export default function AdminComponent(){
    const[message,setMessage]=useState(null);
    const csrfToken = useSelector(state=>state.token.token);
    const handleAdmin= async (e)=>{
        try{
            const response = await apiClient.get("/admin",{
                withCredentials:true// 쿠키를 포함해서 보내겠다고 표시

                }
                );
                setMessage(response.data);

        }catch(error){
            if(error.response && error.response.status===403) {// 롤도 같이 비교해서 접근확인함
                setMessage("관리자 계정만 접근가능합니다.");
            }else if(error.response && error.response.status===401){// 롤도 같이 비교해서 접근확인함
                setMessage(error.response.data.message);
            } else {
                console.log(error);
            }
        }
    }

    const handleLogout= async (e)=>{
        try{
            const response= await axios.post("http://localhost:8080/logout",{}, // 주소 바디 옵션 3가지인데 바디는 안들어감(apiclient로 해도 됨, 바디에 안들어가서)
                { headers:{
                        "X-CSRF-TOKEN": csrfToken
                    },
                    withCredentials: true,
                });
            setMessage(response.data);

        } catch (error){
            console.log(error);
        }
    }

    return(
        <>
            <button onClick={handleLogout}>Logout</button>
            <button onClick={handleAdmin}>관리자 확인</button>
            {message}
        </>
    )
}