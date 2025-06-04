import {useDispatch, useSelector} from "react-redux";
import {productDelete} from "./productSlice";
import { Link } from "react-router-dom";
export default function List(){
    const pdList = useSelector(state=>state.product.productList);
    const dispatch = useDispatch();

    const handleDelete = (e)=>{
        
        fetch("http://localhost:8080/product/"+e.target.id,{
            method: "DELETE",
        })
        .then(response=>{
            if(!response.ok){
                throw new Error("네트워크 오류");
            }
            return response.text();//응답되는 형태->.이름() 함수로 사용가능하게 변환함
        })
        .then(data=>{
            if(data==="product deleted successfully"){
                dispatch(productDelete(e.target.id));
                console.log(data);
            } else {
                console.log(data);
            }
        })
        .catch(error=>{
            console.log(error);
        });
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