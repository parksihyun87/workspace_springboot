import {useRef, useState} from "react";
import axios from "axios";
import {useDispatch, useSelector} from "react-redux";
import {adminLogin, setToken} from "../store";
import {useNavigate} from "react-router-dom";
import apiClient from "../api/axiosInstance";

export default function AdminLogin(){
    const dispatch= useDispatch();
    const [message,setMessage]= useState(null);
    const admin=useSelector(state=>state.userInfo.admin)
    const navigate= useNavigate();
    const handleLogin= async (e)=>{
        try {
            e.preventDefault();
            const response= await apiClient.post("/login",
                new URLSearchParams({
                    username:"ADMIN_"+e.target.id.value,
                    password:e.target.password.value
                })
                );
            await dispatch(setToken(response.headers['authorization']));
            console.log(response.headers['authorization']);
            setMessage(response.data.username);
            console.log(admin);
            dispatch(adminLogin(response.data.username));
            navigate("/");

        } catch (error) {
            if(error.response && error.response.status===409){//컨피그 401?
                setMessage(error.response.data);
            }
            console.log(error);
        }
    }

    return (
        <>
            <form onSubmit={handleLogin}>
                <label>관리자 아이디</label><input type={"text"} name={"id"}/>
                <label>관리자 비밀번호</label><input type={"text"} name={"password"}/>
                <button type={"submit"}>관리자 로그인</button>
            </form>
            {message}
        </>
    );
}


// if(response.data.roles[0].authority==="ROLE_ADMIN"){
// }else{
//     alert("관리자만 접근 가능합니다.");
// }