import {Link, Outlet, useOutletContext} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import apiClient from "./Instance/clientInstance";
import {addList, clearList} from "./MadangSlice";


export default function SearchList(){
    const {outletFlag,pickedCriteria,pickedDate}=useOutletContext();
    let prodList=useSelector((state)=>state.madang.prodList);
    const dispatch= useDispatch();

       useEffect(() => {
           const fetchData = async ()=>{
               try{
                   await dispatch(clearList());
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
                   dispatch(addList(response.data));
               } catch (error) {
                   console.log(error);
               }
           }
           fetchData();
       }, [pickedCriteria,pickedDate]);
        console.log(prodList);
    return(
        <>
            <div>
                {prodList.map((data)=>(
                    <>
                        <div>주문 번호: {data.orderid}</div>
                        <div>주문 고객 아이디: {data.custid}</div>
                        <div>주문 서적 아이디: <Link to ="/searchproduct/searchlist/:prodid">{data.bookid}</Link> </div>
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