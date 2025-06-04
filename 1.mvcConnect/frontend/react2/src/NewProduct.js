import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { productAdd } from "./productSlice";
import { useRef} from "react";

export default function NewProduct(){
    const dispatch = useDispatch();
    const navigate = useNavigate();
    // const imgsrc = "https://dummyimage.com/200x200/00f/fff.jpg&text=product";
    // const id = useRef(4);

    const handleSubmit = e=>{
        e.preventDefault();
        const p = {title: e.target.title.value, price: Number(e.target.price.value)};// 인풋 텍스트가 텍스트라 스트링이여서 넘버로 변환.
        fetch("http://localhost:8080/new-product",{
            method: "POST",
            headers: {
                "Content-Type" : "aplication/json"
            },
            body: JSON.stringify(p), //해당 내용 실행시 제이슨 객체로 변환되어 바디안으로 들어가 전달됨
        })
        .then(response=>{
            if(!response.ok){
                throw new Error("네트워크 오류");
            }
            return response.json();// 리턴값으로 다시 자바스크립트로 변환함
        })
        //다시 반환받은 다음, 우리도 함수에 쓸 값으로 사용함. 
        .then(data=>{
            dispatch(productAdd(data));
        })
        .catch(error=>{
            console.log(error);
        });
        // id.current++;
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