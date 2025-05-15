import { useDispatch, useSelector } from "react-redux";
import { Link, Outlet, useNavigate } from "react-router-dom";
import { onDelete, onModify} from "./saleSlice";

export default function List(){
    const saleArr=useSelector((state)=>(state.sale.arr));
    const dispatch=useDispatch();
    const newArr=[];
    for(let i=0; i<saleArr.length; i++){
                newArr.push(<><Link to ={`/detail/${saleArr[i].id}`}><img src={saleArr[i].imgSrc}/></Link>
                <p id={saleArr[i].id}>{saleArr[i].title}</p>
                <Link to ={`/${saleArr[i].id}`}><button>수정버튼1</button></Link><button /* id={saleArr[i].id} onClick={(e)=>
                    fetch("http://localhost:8080/product/"+e.target.id,{
                        method:"DELETE"
                })
                .then(response=>{
                if(!response.ok){
                    throw new Error("네트워크 오류");
                }
                return response.text();
                })
                .then(data=>{
                    if(data==="product delete successfully"){
                        dispatch(onDelete(Number(e.target.id)));
                        console.log(data);
                    } else{
                        console.log(data);
                    }
                })
                .catch(error=>{
                    console.log(error);
                })} */>삭제버튼2</button>
                <br/>
                </>);
            }
    return(
        <>
            {newArr}        
        </>
    )
}