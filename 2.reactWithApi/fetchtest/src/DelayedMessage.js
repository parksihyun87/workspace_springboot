import { useEffect, useState } from "react";

export default function DelayedMessage(){
    const [visible,setVisible]= useState(false);

    useEffect(()=>{
        const timer= setTimeout(()=>{
            setVisible(true);
        }, 3000);
    },[]);

    return(
        <div>
            {visible?  <p>3초 후에 나타나는 메시지</p> : <p>기다리는 중...</p>}
        </div>
    );
}