import { useRef } from "react";

export default function TextInput(){
    const inputEl = useRef(null);
    // const focusInput= ()=> {
    //     inputEl.current.focus();
    // };
    const focusInput= ()=> {
        inputEl.current.style.backgroundColor="blue";
    };

    return (
        <div>
            {/* <input ref={inputEl} type="text"/> */}
            <div ref={inputEl}>하이미디어 아카데미</div>
            <button onClick={focusInput}>Focus the input</button>
        </div>
    )

}