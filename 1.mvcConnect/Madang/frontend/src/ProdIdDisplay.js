import {Link, useParams} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import apiClient from "./Instance/clientInstance";
import {addProdList, clearProdList} from "./MadangSlice";


export default function ProdIdDisplay(){
    const {bookid}=useParams();
    const bookInfo=useSelector(state=>state.madang.prodList);
    const dispatch=useDispatch();

    useEffect(()=>{
        const fetchData = async ()=>{
            try {
                await dispatch(clearProdList());
                const response = await apiClient.get("/bookinfo/"+bookid);
                dispatch(addProdList(response.data));
            }catch(error){
                console.log(error);
            }
        };
        fetchData();
    }, [])

    return(
        <>
            {bookInfo && <>
                <p>서적 아이디 : {bookInfo.id}</p>
                <p>서적 이름 : {bookInfo.bookName}</p>
                <p>출판사 : {bookInfo.publisher}</p>
                <p>정가 : {bookInfo.price}</p>
                <p><Link to={"/searchproduct/searchlist/detailedorder/"}>주문 기록</Link></p>
                <hr/>
                <Link to={"/searchproduct/searchlist"}>뒤로</Link>
                <hr/>
            </>}
        </>
    )
}