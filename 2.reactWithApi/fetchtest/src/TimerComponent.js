import { useEffect, useState } from "react";

export default function TimerComponent(){
    const[count,setCount]=useState(0);
    useEffect(()=>{
        const interval = setInterval(()=>{
            console.log(`Count:${count}`);
    },1000);
    return ()=>{
        clearInterval(interval);
        console.log('Cleanup function executed');
    };}
    ,[count]);
    return (
        <div>
            <p>count: {count}</p>
            <button onClick={()=>{
                setCount(count+1)
            }}>increase Count</button>
        </div>
    )
}