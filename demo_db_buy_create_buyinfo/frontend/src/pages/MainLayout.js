import {Link, Outlet} from "react-router-dom";
import {useSelector} from "react-redux";

export default function MainLayout(){
    const user = useSelector(state=>state.userInfo.user);
    return (
        <>
            <h1>&lt;고객 관리&gt;</h1>
            { !user?

            <><Link to="/adminlogin">관리자 로그인</Link>|<Link to={"/adminadd"}>관리자 추가</Link>|</>
            :
            <><Link to={"/"}>홈</Link>|<Link to={"/search"}>검색</Link>|
            <Link to={"/create-userinfo"}>고객정보추가</Link></>
            }
            <Outlet/>
        </>
    );
}