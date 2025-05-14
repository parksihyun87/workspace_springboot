import { useDispatch } from "react-redux";
import { onAdd } from "./saleSlice";
import { useNavigate } from "react-router-dom";

export default function NewProduct(){
    let dispatch= useDispatch();
    //const id= useRef(4) 값이 바뀌어도 재랜더링 되지 않고, 값이 저장됨. 얘는 객체여서,
    // id.current 해야 지금 값이 나옴. 
    const navigate= useNavigate();

    return(
        <>
        <form onSubmit={(e)=>{
            e.preventDefault();
            dispatch(onAdd({title:e.target.title.value,price:Number(e.target.price.value)}))
            navigate("/");
        }}>
            <input name="title"></input>
            <input name="price"></input>
            <button type={"submit"}>추가</button>
        </form>
        </>
    )
}