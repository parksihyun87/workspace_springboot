import {Link, useParams} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import apiClient from "./api/clientInstance";
import {addBuy, clearBuy, clearUser} from "./JpaUserSlice";
import {useEffect} from "react";

export default function BuyCondition() {
    const {userid}=useParams();// 파람 그냥 받으면 객체임.
    const select= useSelector(state=>state.user.buyList);
    const dispatch= useDispatch();
    useEffect(() => {
        const buyList= async ()=>{
            try{
            await dispatch(clearBuy());
            const response = await apiClient.get(`buyinfo/buylist/${userid}`)
            await dispatch(clearBuy());
            dispatch(addBuy(response.data));
            } catch (error){
                console.log(error);
            }
        }
        buyList();
    }, [userid]);
    return (
        <>
            --------------------------
            {(select.length === 0) ? (
                "오류입니다."
            ) : (
                select.map((data) => (
                    <>
                    <div key={data.id}>아이디: {data.userid}</div>
                    <div>제품이름: {data.prodname}</div>
                    <div>그룹이름: {data.groupname}</div>
                    <div>가격: {data.price}</div>
                    <div>수량: {data.amount}</div>
                    --------------------------
                    </>
                ))
            )}
            <div>
            <Link to="/displayuserinfo">고객정보 다시보기</Link>
            </div>
        </>
    );
}

