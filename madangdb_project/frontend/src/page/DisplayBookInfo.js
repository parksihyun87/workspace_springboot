import {Link, useParams} from "react-router-dom";
import {useEffect} from "react";
import apiClient from "../api/apiinstance";
import errorDisplay from "../util/errorDisplay";
import {useDispatch, useSelector} from "react-redux";
import {setBookInfo} from "../store";

export default function DisplayBookInfo(){
    const {bookid} = useParams();
    const bookInfo = useSelector(state => state.madangDBInfo.bookInfo);
    const dispatch = useDispatch();

    useEffect(()=>{
        const fetchData = async ()=>{
            try {
                await dispatch(setBookInfo(null));
                const response = await apiClient.get("/bookinfo/"+bookid);
                dispatch(setBookInfo(response.data));
            }catch(error){
                errorDisplay();
            }
        };
        fetchData();
    }, [])

    return (
        <>
            {bookInfo && <>
                <p>서적 아이디 : {bookInfo.bookId}</p>
                <p>서적 이름 : {bookInfo.bookName}</p>
                <p>출판사 : {bookInfo.publisher}</p>
                <p>정가 : {bookInfo.price}</p>
                <p><Link to={"/orderinfo/bookinfo/bookorderinfo"}>주문 기록</Link></p>
                <hr/>
                <Link to={"/orderinfo/data"}>뒤로</Link>
                <hr/>
            </>}

        </>
    );
}
