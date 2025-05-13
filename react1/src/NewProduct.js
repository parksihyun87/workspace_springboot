import { useDispatch } from "react-redux";
import { onAdd } from "./saleSlice";

export default function NewProduct(){
    let dispatch= useDispatch();

    

    return(
        <>
        <form onSubmit={(e)=>{
            e.preventDefault();
            dispatch(onAdd({title:e.target.title.value,price:e.target.price.value}))

        }}>
            <input name="title"></input>
            <input name="price"></input>
            <button type={"submit"}>추가</button>
        </form>
        </>
    )
}