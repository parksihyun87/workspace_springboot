import {Link, Outlet} from "react-router-dom";
import {useSelector} from "react-redux";

export default function DetailedOrdDisplay(){
    const bookInfo = useSelector(state=>state.madang.prodList);

    return (
        <>
            <h2>{bookInfo.bookname}의 주문정보</h2>
            {bookInfo.orders ? bookInfo.orders.map(t => <>
                    <p>주문 번호:{t.orderid}</p>
                    <p>주문 고객이름:{t.custName}</p>
                    <p>판매 가격:{t.salePrice}</p>
                    <p>주문 날짜:{t.orderDate}</p>
                    <hr/>
                </>)
                : "주문정보가 없습니다."}
            <hr/>
            <Link to={"/searchproduct/searchlist/" + bookInfo.id}>뒤로</Link>

            <hr/>
        </>
    );
}