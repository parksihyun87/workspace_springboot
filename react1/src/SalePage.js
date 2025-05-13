import { useSelector } from "react-redux";
import { useParams } from "react-router-dom"

export default function SalePage(){
    // let {params}= useParams();
    let item = document.getElementById("black");
    let text=item.innerText;
    let price= 0;
    let arr=[];
    const payArr= useSelector((state)=>(state.sale.arr));
    for(let m of payArr){
        if(m.title===text){
            price=m.price;
        }
    }
    arr.push(text,price);

    return(
        <>
           <img src="https://dummyimage.com/200x200/00f/fff.jpg&text=prod"/>
           {arr}
        </>
    )
}