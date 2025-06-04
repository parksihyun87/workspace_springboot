import { Outlet } from "react-router-dom";
import Nevigate from "./Navigate";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { productAdd } from "./productSlice";

export default function MainLayout(){
    const dispatch= useDispatch();
    useEffect(()=>{
        fetch("http://localhost:8080/product-list")
        .then(response=>{
            if(!response.ok){
                throw new Error("네트워크 에러");
            }
            return response.json()//백엔드가 보내주는 제이슨 객체가 자바 객체로 변환되어 받는다.
        })
        .then(data=>{
            data.map(p=>dispatch(productAdd(p)));
        })
        .catch(error=>{
            console.log(error);
        });

    },[]);

    return (
        <>
        <h1>TEST STORE</h1>
        <Nevigate></Nevigate>
        <Outlet></Outlet>
        </>
    );
}