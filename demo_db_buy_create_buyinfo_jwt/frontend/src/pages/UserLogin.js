import axios from "axios";
import {setToken, userLogin} from "../store";
import apiClient from "../api/axiosInstance";
import {useDispatch, useSelector} from "react-redux";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

export default function UserLogin(){
    const dispatch= useDispatch();
    const [message,setMessage]= useState(null);
    const user=useSelector(state=>state.userInfo.user)
    const navigate= useNavigate();

    const handleLogin= async (e)=>{
        try {
            e.preventDefault();
            const response = await apiClient.post("/login",
                new URLSearchParams({
                    username:"USER_" + e.target.username.value,
                    password:e.target.password.value,
                })
            );
            await dispatch(setToken(response.headers['authorization']));
            dispatch(userLogin(response.data.username));
            console.log(response.headers['authorization']);
            // console.log("역할: ",response.data.roles[0].authority);
            // role = response.data.roles[0].authority;
            setMessage(response.data.username);
            console.log(user);
            navigate("/");
        } catch (error) {
            if(error.response && error.response.status===409){
                setMessage(error.response.data);
            }
            console.log(error);
        }
    }

    return (
        <>
            <form onSubmit={handleLogin}>
                <label>유저 아이디</label><input type={"text"} name={"username"}/>
                <label>유저 비밀번호</label><input type={"text"} name={"password"}/>
                <button type={"submit"}>유저 로그인</button>
            </form>
            {message}
        </>
    );
}