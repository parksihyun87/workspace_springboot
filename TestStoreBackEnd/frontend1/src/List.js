import {useDispatch, useSelector} from "react-redux";
import {productDelete} from "./productSlice";
import { Link } from "react-router-dom";
import apiClient from "./api/clientInstance";

export default function List(){
    const pdList = useSelector(state=>state.product.productList);
    const dispatch = useDispatch();

    const handleDelete = async (e)=>{
        try{
            const response = await apiClient.delete("/product/"+e.target.id);
            dispatch(productDelete(e.target.id));
            console.log(response.data);
        } catch(error){
            console.log(error);
        }
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
    // <img src={t.imagesrc}/>
    return (
        <>
            {list}
        </>
    );
}