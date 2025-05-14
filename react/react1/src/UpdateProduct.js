import { useState } from "react";
import { useDispatch, useSelector } from "react-redux"
import { useParams } from "react-router-dom";
import { onModify } from "./saleSlice";

export default function UpdateProduct(){
    // 현재 파람스는 id와 같다. set을 가져와서 
    const {params}=useParams();
    const orgArr= useSelector((state)=>(state.sale.arr));
    const[price,setPrice]= useState(orgArr[params-1].price);
    let dispatch=useDispatch();

    return(
        <>  
           <form onSubmit={(e)=>{
            e.preventDefault();
            dispatch(onModify({id:params,price:price}))        
           }}>
                {orgArr[params-1].title}
                <input name="price" value={price} onChange={(e)=>{
                    setPrice(e.target.value);
                    // setstate (스테이트 값을 바꾸는 함수를 넣는데 여기 화살표 함수 형식으로 해도 됨.)
                    // 셋 스테이트는 비동기 처리: 이 함수가 리턴했다고 함수가 끝났다고 할 수 없으므로,
                    
                    // 얘가 속해 있는 함수가 다 종료되고 처리가 된다.

                    // setstate(state+1) 한꺼번에 실행하느라0+ 1
                    // setstate(state+1) 또 0+1. 이거는 셋스테이가 실행될때 호출해라는 약속.
                    // setstate(s=>s+1)두개면 함수 두개 호출해서 실행해라
                    // 하나의 함수안에서 같은 셋스테이트에 대하여 여러번 호출되면 이런식으로 작성하는 것이 좋다.
                    // 강사님 작성에서는 스테이트를 넘길 객체 전체로 잡았다.

                }}></input>
                <button type="submit">수정제출버튼</button>
            </form> 
        </>
    )
}
