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
            const response= await axios.post("http://localhost:8080/login",// 제슨땜에 형식 안맞아서
                new URLSearchParams({//x-www-form-urlencoded 형식
                    username:usernameRef.current.value,
                    password: passwordRef.current.value
                }),{
                    headers:{
                        "X-CSRF-TOKEN": csrfToken
                    },
                    withCredentials:true
                }
            );
            dispatch(setToken(response.data['csrf-token']));
            console.log(response.data['csrf-token']);
            setMessage(response.data.username);//response.data.role[0].authority
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
            },{
                headers:{
                    "X-CSRF-TOKEN": csrfToken
                },
                withCredentials:true
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