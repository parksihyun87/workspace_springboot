import {Link, Outlet} from "react-router-dom";
import {useSelector} from "react-redux";

export default function ManinLayout() {
    const user= useSelector(state=>state.post.user);

    return (

        <div>
            <div><Link to={"/"}><h1>게시판</h1></Link></div>
            {user?<>
                <Link to={"/boardlist"}>게시판</Link> ||
                <Link to={"/userlogout"}>로그아웃</Link>
                </>
                :
                <>
                <Link to={"/userlogin"}>로그인</Link> ||
                <Link to={"/join"}>회원가입</Link> ||
                </>
            }
            <Outlet/>
        </div>
    );
}
