import {useRef, useState} from "react";
import apiClient from "./apiInstance/apiInstance";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {setToken} from "./store";

export default function Login(){
        const usernameRef= useRef();
        const passwordRef= useRef();
        const [message,setMessage]=useState(null);
        const navigate= useNavigate();
        const csrfToken= useSelector(state=>state.token.token);
        const dispatch= useDispatch();

    const handleLogin = async ()=>{
        try {
            const response= await apiClient.post("/login",// * 로그인으로 기본임. 포스트 요청해야 함. 여기도 서치파람 형식임.
                new URLSearchParams({//x-www-form-urlencoded 형식
                    username:usernameRef.current.value,
                    password: passwordRef.current.value
                })//* 헤더 쿠키 삭제.
            );
            console.log(response.data.role);
            dispatch(setToken(response.headers["authorization"]));// 대문자로 jwtloginfilter에서 Authorization했지만, 여긴 소문자로 해야 함. 프론트에서 헤더키에 이름은 대소를 구분안하기에 관례상 이렇게 시작함.
            console.log(response.headers["authorization"]);

            navigate("/admin");
        } catch (error){
            if(error.response && error.response.status===401){
                setMessage(error.response.data.result)
            }else{
                console.log(error);
            }
        }
    }

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