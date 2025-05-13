import { useSelector } from "react-redux";
import { useParams } from "react-router-dom"

export default function SalePage(){
    let {id}= useParams();
    let newArr=[];
    const payArr= useSelector((state)=>(state.sale.arr));
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
        </>
    )
}