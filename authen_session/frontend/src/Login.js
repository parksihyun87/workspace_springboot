import {useRef, useState} from "react";
import apiClient from "./apiInstance/apiInstance";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export default function Login(){
        const usernameRef= useRef();
        const passwordRef= useRef();
        const [message,setMessage]=useState(null);
        const navigate= useNavigate();

    const handleJoin = async ()=>{
        try {
            const response = await apiClient.post("/join", {
                username: usernameRef.current.value,
                password: passwordRef.current.value
            });// 집어 넣을 값
            setMessage(response.data);
        } catch (error){
            if(error.response && error.response.status===409){// 400번대나 500번대라는 이야기이다. 아예 오류가 안나면(코스 오류등으로 서버에 전달도 안될떄는)
                setMessage(error.response.data);
            } else {
                console.log(error);
            }
        }
    }

    const handleLogin = async ()=>{
        try {
            const response= await axios.post("http://localhost:8080/login",// 제슨땜에 형식 안맞아서
                new URLSearchParams({//x-www-form-urlencoded 형식
                    username:usernameRef.current.value,
                    password: passwordRef.current.value
                }),{
                withCredentials:true// 이걸 넣어야 찌꺼기가 사라지고 유효한 세션을 얻음.위드 크레덴셜로 제대로 된 쿠키 세션아이디를 받아야 인증이 됨.
                }
                );
            setMessage(response.data.username);//response.data.role[0].authority

            // navigate("/admin");
        } catch (error){
            if(error.response && error.response.status===401){
                setMessage(error.response.data.result)
            }else{
                console.log(error);
            }
        }
    }

    return(
        <>
            <form>
                <input type={"text"} name={"username"} ref={usernameRef} placeholder={"아이디입력"}/>
                <input type={"password"} name={"password"} ref={passwordRef} placeholder={"패스워드 입력"}/>
                <button type={"button"} name={"login"} onClick={handleLogin}>Login</button>
                <button type={"button"} name={"join"} onClick={handleJoin}>Join</button>
            </form>
            {message}
        </>
    )
}