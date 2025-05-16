import {useParams} from "react-router-dom";
import {useSelector} from "react-redux";


export default function DetailProduct(){
    const {id} = useParams();
    const product = useSelector(state=>state.product.productList.find(t=>t.id === Number(id)));
    if(product === undefined){
        console.log("없는 상품입니다.");
    }
    return (
        <>
            {product && (
                <div>
                    <img src={product.imagesrc} />
                    <p>{product.title}</p>
                    <p>{product.price}</p>
                </div>
            )}
        </>
    );
}