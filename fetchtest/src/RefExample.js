import { useRef } from "react";

export default function RefExample(){
    const myRef= useRef(0);

    const handleClick=()=> {
        console.log('before:', myRef.current);
        myRef.current +=1;
        console.log('After:', myRef.current);
    };

    return <button onClick={handleClick}>increase Ref</button>;
}