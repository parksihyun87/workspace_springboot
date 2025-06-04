import {useDispatch, useSelector} from "react-redux";
import {productDelete} from "./productSlice";
import { Link } from "react-router-dom";
export default function List(){
    const pdList = useSelector(state=>state.product.productList);
    const dispatch = useDispatch();

    const handleDelete = (e)=>{
        dispatch(productDelete(e.target.id));
    };

    const list = pdList.map(t=>(
        <div key={t.id}>
            <br/>
            <Link to={"/detail-product/"+t.id}><img src={t.imagesrc}/></Link>
            <h3>{t.title}</h3>
            <Link to={"/edit-product/"+t.id}> 🖋 </Link>
            <span id={t.id} onClick={handleDelete}>🗑</span>
            <br/><br/>
        </div>
    ));
    
    return (
        <>
            {list}
        </>
    );
}