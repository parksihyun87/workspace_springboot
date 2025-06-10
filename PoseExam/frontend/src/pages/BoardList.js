import {useEffect} from "react";
import apiClient from "../api/ApiInstance";
import {useDispatch, useSelector} from "react-redux";
import {setPostList} from "../store";
import {Link} from "react-router-dom";

export default function BoardList() {
    const dispatch= useDispatch();
    const fetchPostList=useSelector(state=>state.post.postList);

    useEffect(() => {
        const fetchData= async ()=>{
            try {
                const response= await apiClient.get("/readlist");
                dispatch(setPostList(response.data));
                console.log(response.data);
            } catch (error){
                console.log("에러발생", error);
            }
        }
        fetchData();
    }, []);

    return (
        <div>
            보드리스트 입니다.
            {fetchPostList  && fetchPostList.map(e=>(<div key={e.id}>
                <Link to={"/boardlist/"+e.id}>
                    <span>제목: {e.title} </span>
                    <span>이름: {e.writer} </span>
                    <span>날짜: {e.pdate} </span>
                </Link>
                </div>
            ))}
        </div>
    );
}
