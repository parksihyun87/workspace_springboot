import { Outlet } from "react-router-dom";
import Nevigate from "./Navigate";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { productAdd } from "./productSlice";
import apiClient from "./api/clientInstance";

export default function MainLayout(){
    const dispatch= useDispatch();
    useEffect(()=>{

        const fetchData = async ()=>{
            try{
                const response = await apiClient.get("product-list");// 오류나면 자동으로 잡고, 그리고 경로도 설정.
                response.data.map(p=>dispatch(productAdd(p)));
            } catch(error){
                console.log(error);
            }
        }
        fetchData();
    },[]);
    return (
        <>
        <h1>TEST STORE</h1>
        <Nevigate></Nevigate>
        <Outlet></Outlet>
        </>
    );
}
// 바로 실행해줌.
//     fetch("http://localhost:8080/product-list")
//     .then(response=>{
//         if(!response.ok){
//             throw new Error("네트워크 에러");
//         }
//         return response.json()//백엔드가 보내주는 제이슨 객체가 자바 객체로 변환되어 받는다.
//     })
//     .then(data=>{
//         data.map(p=>dispatch(productAdd(p)));
//     })
//     .catch(error=>{
//         console.log(error);
//     });
