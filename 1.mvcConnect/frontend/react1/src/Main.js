import { useDispatch } from "react-redux";
import { Link, Outlet } from "react-router-dom";
import { onAdd } from "./saleSlice";
import { useEffect } from "react";

export default function Main(){
    const dispatch= useDispatch();
    useEffect(()=>{
        fetch("http://localhost:8080/product-list")// get 요청,, 옵션 생략, 바디도 없음. 
    .then((response)=>{
        if(!response.ok){//(!,변수, .,ok) 4가지를 같이 써서 유효한지 확인
            throw new Error("네트워크 오류")
        }
        return response.json();// 실제들어 있는 데이타의 유형을 가리키면 거의 맞다.(받는 사람에 맞게 조정됨.)
        // js화 됨
    })
    .then((data)=>{
       data.map((e)=>dispatch(onAdd(e)));
    })
    .catch((error)=>{
        console.log(error);
    });
    },[]);

    return(
        <>
            <h1>TEST STORE</h1>
            <Link to ="/"><h2>LIST |</h2></Link>
            <Link to ="/newproduct"><h2>NEW PRODUCT</h2></Link>
            <Outlet/>
        </>
    )
}