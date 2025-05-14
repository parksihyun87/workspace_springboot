import { useEffect } from "react";

export default function ExampleComponent2(){
    useEffect(()=>{
        console.log('component mounted');
        return()=>{
            console.log('component unmount');
        };
    },[]);
    return <div>Example Component</div>;
}