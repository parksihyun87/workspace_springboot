import { useDispatch, useSelector } from "react-redux";
import { Link, Outlet, useNavigate } from "react-router-dom";
import { onDelete, onModify} from "./saleSlice";

export default function List(){
    const saleArr=useSelector((state)=>(state.sale.arr));
    const dispatch=useDispatch();
    let number=1;
    const newArr=[];
    for(let i=0; i<saleArr.length; i++){
                newArr.push(<><Link to ={`/Main/${number}`}><img src="https://dummyimage.com/200x200/00f/fff.jpg&text=prod"/></Link>
                <p id={saleArr[i].id}>{saleArr[i].title}</p>
                <Link to ={`/${number}`}><button>수정버튼1</button></Link><button onClick={(e)=>{dispatch(onDelete(saleArr[i].id))}}>삭제버튼2</button>
                <br/>
                </>);
                {number++}
            }
    return(
        <>
            {newArr}        
        </>
    )
}