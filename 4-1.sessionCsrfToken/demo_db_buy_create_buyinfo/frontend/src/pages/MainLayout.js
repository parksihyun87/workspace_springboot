import {Link, Outlet} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import apiClient from "../api/axiosInstance";
import {setToken} from "../store";

export default function MainLayout(){
    const dispatch= useDispatch();
    const logStatus=useSelector(state=>state.userInfo.flagLogStatus);
    const admin=useSelector(state=>state.userInfo.admin);
    const user=useSelector(state=>state.userInfo.user);
    const content=null;
    useEffect(() => {
        const fetchData= async ()=>{
            try {
                const response= await apiClient.get("/csrf-token",{
                    withCredentials:true
                });
                dispatch(setToken(response.data["csrf-token"]));
                console.log(response.data["csrf-token"]);
                console.log("페치안 유저:",user);
                console.log("페치안 어드민:",admin);
            } catch (error){
                console.log(error);
            }
        }
        console.log("유저:",user);
        console.log("어드민:",admin);
        fetchData();
    }, [logStatus]);

    return (
        <>
            <Link to={"/"}><h1>&lt;고객 관리&gt;</h1></Link>
            { !admin && !user ?
            <><Link to="/userlogin">일반 로그인</Link>|<Link to="/adminlogin">관리자 로그인</Link>|<Link to={"/adminadd"}>관리자 추가</Link>|</>
            :admin?
            <><Link to={"/"}>홈</Link>|<Link to={"/search"}>검색</Link>|
            <Link to={"/create-userinfo"}>고객정보추가</Link>|<Link to ="/adminlogout">관리자 로그아웃</Link></>:
            <><Link to={"/"}>홈</Link>|<Link to={"/search"}>검색</Link>|<Link to ="/userlogout">일반 로그아웃</Link></>
            }
            <Outlet/>
        </>
    );
}

// 둘다 널, ad 존재, user존재
// if(admin===null && user===null){
//     content=일반로그인, 관리자로그인, 관리자 추가
// } else if(!admin){
//     content=홈, 검색, 고객정보추가, 관리자로그아웃
// } else if(!user){
//     content=홈,검색, 로그아웃
// }
