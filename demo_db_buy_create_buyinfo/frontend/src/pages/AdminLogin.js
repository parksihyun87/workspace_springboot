import {useRef, useState} from "react";
import axios from "axios";
import {useDispatch, useSelector} from "react-redux";
import {adminLogin, setToken} from "../store";
import {useNavigate} from "react-router-dom";

export default function AdminLogin(){
    const csrfToken= useSelector(state=>state.userInfo.token);
    const dispatch= useDispatch();
    const [message,setMessage]= useState(null);
    const admin=useSelector(state=>state.userInfo.admin)
    const navigate= useNavigate();
    const handleLogin= async (e)=>{
        try {
            e.preventDefault();
            const response= await axios.post("http://localhost:8080/login",
                new URLSearchParams({
                    username:e.target.id.value,
                    password:e.target.password.value
                })
                , {
                    headers:{
                        "X-CSRF-TOKEN": csrfToken
                    },
                    withCredentials: true
                });
            await dispatch(setToken(response.data['csrf-token']));
            console.log(response.data['csrf-token']);
            setMessage(response.data.username);
            console.log(admin);

            if(response.data.roles[0].authority==="ROLE_ADMIN"){
                dispatch(adminLogin(response.data.username));
                navigate("/");
            }else{
                alert("관리자만 접근 가능합니다.");
            }
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