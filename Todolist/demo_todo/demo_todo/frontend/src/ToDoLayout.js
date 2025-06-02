
import { NavLink, Outlet} from 'react-router-dom';
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import axios from "axios";
import {addToDoList} from "./toDoSlice";
import apiClient from "./api/axiosInstance";

function ToDoLayout() {
    const dispatch=useDispatch();

    useEffect(() => {
     const fetchData=async ()=>{
         try{
             const response=await apiClient.get("/todo-list");
             response.data.map(t=> dispatch(addToDoList(t)));
         }catch(error){
             if(error.code==="ECONNABORTED"){
                 console.log("응답 시간 초과");
             }else if(error.response){
                 console.log(error.response.status);
                 console.log(error.response.data);
             }else if(error.request){
                 console.log(error.request);
                 console.log(error.message);
             }else{
                 console.log(error.message);
             }
         }
     }
        fetchData();
    }, []);


    return (
        <div>
            <h1>To-Do List</h1>
            <nav>
                <NavLink to="/todo" end>To-Do 목록</NavLink> |
                <NavLink to="/todo/add">Add To-Do</NavLink>
            </nav>
            <Outlet/>
        </div>
    );
}

export default ToDoLayout;


