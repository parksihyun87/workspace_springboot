import {Link, Outlet, useOutletContext} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import apiClient from "./Instance/clientInstance";
import { addOrderedList, clearOrderedList} from "./MadangSlice";
import errorDisplay from "./errorDIsplay";


export default function SearchList(){
    const {outletFlag,pickedCriteria,pickedDate}=useOutletContext();
    let orderedList=useSelector((state)=>state.madang.orderedList);
    const dispatch= useDispatch();

    useEffect(() => {
        const fetchData = async ()=>{
            try{
                await dispatch(clearOrderedList());
                let url=null;
                if(pickedCriteria==="before"){
                    url="orderinfo/orderbeforelist";
                } else if(pickedCriteria==="after"){
                    url="orderinfo/orderafterlist";
                }
                const response= await apiClient.get(url,{
                    params: {
                        orderdate : pickedDate}
                });
                dispatch(addOrderedList(response.data));
            } catch (error) {
                errorDisplay(error);
            }
        }
        fetchData();
    }, [pickedCriteria,pickedDate]);
    console.log(orderedList);
    return(
        <>
            <div>
                {orderedList.map((data)=>(
                    <>
                        <div>주문 번호: {data.orderid}</div>
                        <div>주문 고객 아이디: {data.custid}</div>
                        <div>주문 서적 아이디: <Link to ={"/searchproduct/searchlist/"+data.bookid}>{data.bookid}</Link> </div>
                        <div>주문 가격: {data.saleprice}</div>
                        <div>주문 일자: {data.orderdate}</div>
                        ------------------------------------
                    </>
                ))
                }
            </div>
        </>
    )
}