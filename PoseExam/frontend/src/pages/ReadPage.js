import {useEffect} from "react";
import apiClient from "../api/ApiInstance";
import {useDispatch, useSelector} from "react-redux";
import {setPostList} from "../store";
import {Link, useNavigate, useParams} from "react-router-dom";

export default function ReadPage() {
    const dispatch= useDispatch();
    const fetchPostList=useSelector(state=>state.post.postList);
    const {pid}= useParams();
    const detailedPost= fetchPostList.filter(e=>e.id===Number(pid));
    const navigate = useNavigate();

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

    const handlegomodify= ()=>{
        navigate("/modify")
    }
    const handledelete= ()=>{
        navigate("/delete")
    }

    return (
        <div>
            {detailedPost  && detailedPost.map(e=>(<div key={e.id}>
                    <div>제목: {e.title} </div>
                    <div>이름: {e.writer} </div>
                    <div>날짜: {e.pdate} </div>
                    <div>내용: {e.body}</div>
                    <button onClick={handlegomodify}>수정하기</button>&nbsp;&nbsp;
                    <button onClick={handledelete}>삭제하기</button>
                </div>
            ))}
        </div>
    );
}
