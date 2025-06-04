import {useSelector} from "react-redux";
import {Link} from "react-router-dom";

export default function DisplayBookOrderInfo(){
    const bookInfo = useSelector(state => state.madangDBInfo.bookInfo);

    return (
        <>
            <h2>{bookInfo.bookName}의 주문정보</h2>
            {bookInfo.orders ? bookInfo.orders.map(t => <>
                    <p>주문 번호:{t.orderId}</p>
                    <p>주문 고객이름:{t.custName}</p>
                    <p>판매 가격:{t.salePrice}</p>
                    <p>주문 날짜:{t.orderDate}</p>
                    <hr/>
                </>)
                : "주문정보가 없습니다."}
            <hr/>
            <Link to={"/orderinfo/bookinfo/" + bookInfo.bookId}>뒤로</Link>
            <hr/>
        </>
    );
}