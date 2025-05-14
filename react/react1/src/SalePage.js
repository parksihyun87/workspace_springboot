import { useSelector } from "react-redux";
import { useParams } from "react-router-dom"

export default function SalePage(){
    let {id}= useParams();
    let newArr=[];
    const payArr= useSelector((state)=>(state.sale.arr));// 그냥 여기다가 파인드 해서 가져오면 좋음.
    for(let m of payArr){
        if(Number(m.id)===Number(id)){
           newArr.push(m.title, m.price);
          
        }
    }

    return(
        <>
           <img src="https://dummyimage.com/200x200/00f/fff.jpg&text=prod"/>
           <br/>
           {newArr}

           //product(0이나 언디파인 널)이면 펄스라고 봄 '&&'' 해서 식 넣어주면 참일때만 표시함.

        </>
    )
}