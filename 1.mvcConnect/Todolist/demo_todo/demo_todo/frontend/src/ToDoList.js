import React from 'react';
import {useDispatch, useSelector} from "react-redux";
import {addToDoList, completedToDoList, deleteToDoList} from "./toDoSlice";
import axios from "axios";
import apiClient from "./api/axiosInstance";

function ToDoList() {
    const toDoList=useSelector(state=>state.toDoList.toDoList);
    const dispatch=useDispatch();

    const handleComplete=async (e)=>{
        try {
            const response = await apiClient.put("/todo/" + e.target.id, );
            dispatch(completedToDoList(response.data));

        }catch(error){
            if(error.code==="ECNNABORTED"){
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
    };

    const handleDelete=async ()=>{
        try{
            const response=await apiClient.delete("/completed-todo");
            await dispatch(deleteToDoList());
            const response2=await apiClient.get("/todo-list");
            response2.data.map((t)=> dispatch(addToDoList(t)));


        }catch(error){
            if(error.code==="ECNNABORTED"){
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

    return (
        <div>
            <h2>To-Do 목록</h2>
            <ul>
                {toDoList.map(todo => (
                    <li key={todo.id}>
                        <span style={{textDecoration: todo.completed ? 'line-through' : 'none'}}>{todo.title}</span>
                        <button id={todo.id} onClick={handleComplete}>완료</button> {/* 완료 버튼 */}
                    </li>
                ))}
            </ul>
            <button onClick={handleDelete}>완료 TODO 삭제</button>
        </div>
    );
}

export default ToDoList;
