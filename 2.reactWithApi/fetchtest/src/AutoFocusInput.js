import { useEffect, useRef } from "react";

export default function AutoFocusInput(){
    const inputRef = useRef(null);
    useEffect(()=>{
        if(inputRef.current){
            inputRef.current.focus();// 포커스 함수는 거기에 포인터 활성화하는 함수
        }
    },[]);
    return(
        <div>
        <input ref={inputRef} type ="text"
        placeholder="자동으로 포커스됩니다."></input>
        </div>
    );
}