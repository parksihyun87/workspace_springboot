import {Link, Outlet} from "react-router-dom";

export default function Header() {
    return (
        <>
            <h1>사람찾는 사이트</h1>
            <div>
                <Link to="/Home">홈</Link> &nbsp;&nbsp;||&nbsp;&nbsp;
                <Link to="/ConditionSelect">검색목록</Link> &nbsp;&nbsp;||&nbsp;&nbsp;
                <Link to="/addcustomer">고객정보추가</Link>
            </div>
            <Outlet/>
        </>
    );
}

