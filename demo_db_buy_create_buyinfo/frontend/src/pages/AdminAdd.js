import {useRef, useState} from "react";
import axios from "axios";
import {useDispatch, useSelector} from "react-redux";
import {setToken} from "../store";
import apiClient from "../api/axiosInstance";

export default function AdminAdd(){
    const csrfToken= useSelector(state=>state.userInfo.token);
    const dispatch= useDispatch();
    const [message,setMessage]= useState(null);
    const handleJoin= async (e)=>{
        try {
            e.preventDefault();
            const response = await apiClient.post("/joinadmin",
                {
                    confirm:e.target.confirm.value,
                    username:e.target.id.value,
                    password:e.target.password.value
                }
                , {
                    headers:{
                        "X-CSRF-TOKEN": csrfToken
                    },
                    withCredentials: true
                });
            setMessage(response.data);//
        } catch (error) {
            if(error.response && error.response.status===401){// 컨피그 조인 컨트롤러 패일러
                setMessage(error.response.data);
            }
            console.log(error);
        }
    }

    return (
        <>
            <form onSubmit={handleJoin}>
                <label>관리자 인증번호</label><input type={"text"} name={"confirm"}/>
                <label>관리자 아이디</label><input type={"text"} name={"id"}/>
                <label>관리자 비밀번호</label><input type={"text"} name={"password"}/>
                <button type={"submit"}>관리자 추가</button>
            </form>
            {message}
        </>
    );
}