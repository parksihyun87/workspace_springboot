import {Link, Outlet} from "react-router-dom";


export default function Header(){

    return(
        <>
            <h1>&lt;Madang 서점 관리&gt;</h1>
            <Link to="/">홈</Link> &nbsp;|&nbsp; <Link to="/searchproduct">주문정보확인</Link>
            <Outlet></Outlet>
        </>
    ) 
}