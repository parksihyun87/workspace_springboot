import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { productAdd } from "./productSlice";
import { useRef} from "react";
import apiClient from "./api/clientInstance";

export default function NewProduct(){
    const dispatch = useDispatch();
    const navigate = useNavigate();
    // const imgsrc = "https://dummyimage.com/200x200/00f/fff.jpg&text=product";
    // const id = useRef(4);

    const handleSubmit = async e=>{
        e.preventDefault();
        const p = {title: e.target.title.value, price: Number(e.target.price.value)};// 인풋 텍스트가 텍스트라 스트링이여서 넘버로 변환.
        try{
            const response =  await apiClient.post("/new-product",p);
            dispatch(productAdd(response.data));
        } catch(error) {
            console.log(error);
        }
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

/*fetch("http://localhost:8080/new-product",{
method: "POST",
headers: {
    "Content-Type" : "application/json"
},
body: JSON.stringify(p), //해당 내용 실행시 제이슨 객체로 변환되어 바디안으로 들어가 전달됨
});
if(!response.ok){
    throw new Error("네트워크 오류");
}
const data = await response.json();
dispatch(productAdd(data));*/