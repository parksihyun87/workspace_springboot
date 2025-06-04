import {Link, Outlet} from "react-router-dom";


export default function AddCustomer() {

    return (
        <>
            <Link to="/addcustomer/detailedadduser">가입 정보 추가</Link>&nbsp;&nbsp;
            <Link to="/addcustomer/detailedaddbuy">구매 기록 추가</Link>
            <Outlet></Outlet>
        </>
    );
}
