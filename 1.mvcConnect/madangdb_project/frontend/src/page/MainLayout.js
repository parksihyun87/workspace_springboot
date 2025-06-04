import {Link, Outlet} from "react-router-dom";

export default function MainLayout(){
    return (
        <>
            <h1>&lt;Madang 서점 관리&gt;</h1>
            <Link to={"/"}>홈</Link> |
            <Link to={"/orderinfo"}>주문정보확인</Link>
            <hr/>
            <Outlet></Outlet>
        </>
    );
}