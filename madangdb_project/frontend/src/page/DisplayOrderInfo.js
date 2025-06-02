import {useDispatch, useSelector} from "react-redux";
import {Link} from "react-router-dom";

export default function DisplayOrderInfo(){
    const dispath = useDispatch();
    const orderList = useSelector(state=>state.madangDBInfo.orderdList);


    return (
        <>{(orderList.length !==0) && orderList.map(t=> <>
            <p>주문번호 : {t.orderId}</p>
            {/*<p>주문고객아이디 : <Link to={"/orderinfo/custinfo/" + t.bookId}>{t.custId}</Link></p>*/}
            <p>주문고객아이디 :{t.custId}</p>
            <p>주문서적아이디 : <Link to={"/orderinfo/bookinfo/" + t.bookId}>{t.bookId}</Link></p>
            <p>주문가격 : {t.salePrice}</p>
            <p>주문일자 : {t.orderDate}</p>
            <hr/>
        </>)}

        </>
    );
}