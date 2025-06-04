import { useEffect, useState } from "react";

export default function ExampleComponent(){
    const[count, setCount]= useState(0);

    useEffect(()=>{
        console.log(`count Value updated to: ${count}`)
    }, [count]
    );
    return(
        <div>
            <p>{count}</p>
            <button onClick={()=>{
                setCount(count+1)
            }}>increase count</button>

        </div>
    )
}