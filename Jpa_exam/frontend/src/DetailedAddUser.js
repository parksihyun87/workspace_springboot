import {useDispatch} from "react-redux";
import apiClient from "./api/clientInstance";
import {Outlet, useNavigate} from "react-router-dom";
import {useRef, useState} from "react";

export default function DetailedAddUser() {
    const dispatch=useDispatch();
    const navigate = useNavigate();
    const useridRef = useRef();
    const handlesubmit= async(e)=>{
        e.preventDefault();
        const p= {
            userid: e.target.userid.value,
            username:e.target.username.value,
            birthyear: Number(e.target.birthyear.value),
            addr:e.target.addr.value,
            mobile1:e.target.phone.value.slice(0,3),
            mobile2:e.target.phone.value.slice(3),
            height:Number(e.target.height.value),
            mdate:e.target.mdate.value
        }
        try{
            const response= await apiClient.post("/userinfo/adduser",p);
            navigate("/addcustomer/detailedadduser/addquestion")
        } catch (error){
            console.log(error);
        }
    }

    const handleDupli= async(e)=>{
        // const p1= document.forms.myform.userid.value;
        const p2=useridRef.current.value;
        try{
            const response= await apiClient.get(`/userinfo/userid/${p2}`);
            if(response.data){
                alert("중복입니다.")
            } else {
                alert("사용가능합니다.")
            }
        } catch (error){
            console.log(error);
        }
    }

    return (
        <>
            <div>
            디에드유저
                <form onSubmit={handlesubmit}>&nbsp;
                    <div><label>고객아이디</label>&nbsp;&nbsp;&nbsp;<input name={"userid"} ref={useridRef} type={"text"}/>&nbsp;&nbsp;&nbsp;<button type={"button"} onClick={handleDupli}> 아이디 중복체크</button></div>
                    <div><label>고객이름</label>&nbsp;&nbsp;&nbsp;<input name={"username"} type={"text"}/></div>
                    <div><label>전화번호</label>&nbsp;&nbsp;&nbsp;<input name={"phone"} type={"text"}/></div>
                    <div><label>출생년도</label>&nbsp;&nbsp;&nbsp;<input name={"birthyear"} type={"text"}/></div>
                    <div><label>신장</label>&nbsp;&nbsp;&nbsp;<input name={"height"} type={"text"}/></div>
                    <div><label>거주지역</label>&nbsp;&nbsp;&nbsp;<input name={"addr"} type={"text"}/></div>
                    <div><label>가입일자</label>&nbsp;&nbsp;&nbsp;<input name={"mdate"} type={"date"}/></div>
                    <button type={"submit"}>가입제출</button>
                </form>
            </div>
            <Outlet></Outlet>
        </>
    );
}