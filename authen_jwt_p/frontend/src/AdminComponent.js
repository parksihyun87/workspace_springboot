import {useState} from "react";
import apiClient from "./apiInstance/apiInstance";
import axios from "axios";
import {useDispatch, useSelector} from "react-redux";
import {setToken} from "./store";

export default function AdminComponent(){
    const dispatch = useDispatch();
    const[message,setMessage]=useState(null);
    const csrfToken = useSelector(state=>state.token.token);

    const handleAdmin= async (e)=>{
        try{
            const response = await apiClient.get("/admin");
                setMessage(response.data);
        }catch(error){
            if(error.response && error.response.status===403) {// 롤도 같이 비교해서 접근확인함
                setMessage(error.response.data);
            }else if(error.response && error.response.status===401){// 롤도 같이 비교해서 접근확인함
                setMessage(error.response.data.message);
            } else {
                console.log(error);
            }
        }
    }

    const handleLogout= async (e)=>{
        dispatch(setToken(null));
        setMessage("로그아웃되었습니다.");
    }

    return(
        <>
            <button onClick={handleLogout}>Logout</button>
            <button onClick={handleAdmin}>관리자 확인</button>
            {message}
        </>
    )
}