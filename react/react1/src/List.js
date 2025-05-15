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
                <Link to ={`/${saleArr[i].id}`}><button>수정버튼1</button></Link><button onClick={(e)=>{dispatch(onDelete(saleArr[i].id))}}>삭제버튼2</button>
                <br/>
                </>);
            }
    return(
        <>
            {newArr}        
        </>
    )
}