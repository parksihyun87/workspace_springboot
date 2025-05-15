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
            e.preventDefault();// 새로 만들려면, id는 자동 생성이고, post를 해야 한다. 
            // 담을 내용 p로 적은 후 전달
            const p={title: e.target.title.value, price: Number(e.target.price.value)};
            fetch("http://localhost:8080/product-post",{
                method: "POST",
                headers: {
                    "Content-Type" : "application/json"
                },
                body: JSON.stringify(p),// 문자열에서 제이슨 형식으로 바뀜
        }) //유,중,메,헤,중(컨타:어/제),바(제스프)
        // u,{},m,h,{},ct,aj,b,j.s(p)
            // 헤더에 담는 정보 따로 변형 없어서 json 형식으로 씀.
            // 문자열 객체를 제이슨 형태로 바꿈.
        .then(response=>{
            if(!response.ok){
                throw new Error("네트워크 오류입니다.");
            }
            return response.json();
        })
        .then(data=>{
            dispatch(onAdd(data));
            navigate("/");
        })
        .catch(error=>{
            console.log(error);
        });
            
        }}>
            <input name="title"></input>
            <input name="price"></input>
            <button type="submit">추가</button>
        </form>
        </>
    )
}