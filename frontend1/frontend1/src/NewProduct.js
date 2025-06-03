import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { productAdd } from "./productSlice";
import { useRef} from "react";

export default function NewProduct(){
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const imgsrc = "https://dummyimage.com/200x200/00f/fff.jpg&text=product";
    const id = useRef(4);

    const handleSubmit = e=>{
        e.preventDefault();
        dispatch(productAdd({id: id.current, title: e.target.title.value, price : Number(e.target.price.value), imagesrc:imgsrc}));
        id.current++;
        navigate("/");
    }

    return (
        <>
        <h2>New Product</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" name="title"></input>
                <input type="text" name="price"></input>
                <button type="submit">저장</button>
            </form>

        </>
    );
}