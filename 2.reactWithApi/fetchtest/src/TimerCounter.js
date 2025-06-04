import { useEffect, useState } from "react";

export default function TimerCounter(){
    const[count, setCount]= useState(0);

    useEffect(()=>{
        const interval = setInterval(()=>{
            setCount((prev)=>prev+1);}, 1000);

            return ()=> clearInterval(interval);

        },[]);
        return <p>경과 시간: {count}초</p>;
 }