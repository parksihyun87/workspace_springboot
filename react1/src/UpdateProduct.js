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
                }}></input>
                <button type="submit">수정제출버튼</button>
            </form> 
        </>
    )
}
