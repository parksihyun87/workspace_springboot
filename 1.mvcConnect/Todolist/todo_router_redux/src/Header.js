import { useDispatch } from "react-redux";
import { addToDo } from "./todoSlice";
import { useState } from "react";
import {Link, useNavigate} from "react-router-dom";
import apiClient from "./api/apiInstance";


export default function Header(){
    const dispatch = useDispatch();  
    const navigate = useNavigate();

    const handleSubmit= async e=>{
        e.preventDefault();
        const p ={task:e.target.todo.value.trim()};
        try {
            if(p){
                const response = await apiClient.post("/addtodolist",p);
                dispatch(addToDo(response.data));
                e.target.todo.value="";
                navigate("/todolist");
            }
        } catch (error){
          console.log(error);
        }
    }

    return (
        <>
        <h1>To-Do List</h1>
        <Link to="/todolist">List</Link>&nbsp;&nbsp;||&nbsp;&nbsp;
        <Link to="">Add to do</Link>
        <form onSubmit={handleSubmit}>
        <input type="text" name="todo" placeholder="Add a new task"></input>
      <input type="submit" value="Add Todo"></input>
      </form>
        </>
    );
}