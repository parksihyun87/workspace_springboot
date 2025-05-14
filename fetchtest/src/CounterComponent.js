import { useEffect, useRef, useState } from "react";

export default function CounterComponent(){
    const[count,setCount]=useState(0);
    const previousCountRef=useRef(0);

    useEffect(()=>{
        console.log(`current count: ${count}, previous count:
            ${previousCountRef.current}`);
        previousCountRef.current = count;
    },[count]
    );
    
    return(
        <div>
            <p>Current Count: {count}</p>
            <button onClick={()=> setCount(count+1)}>
                increase count
            </button>
        </div>
    )
}