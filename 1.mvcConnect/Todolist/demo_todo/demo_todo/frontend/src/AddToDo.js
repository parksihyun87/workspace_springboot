import React from 'react';
import {addToDoList} from "./toDoSlice";
import {useDispatch} from "react-redux";
import {useRef} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import apiClient from "./api/axiosInstance";

function AddToDo() {
    const dispatch=useDispatch();
    const navigate=useNavigate();

    const handleSubmit = async (e) => {
        try{
            e.preventDefault();
            const title = {title:e.target.task.value};
            const response=await apiClient.post("/todo", title );
            dispatch(addToDoList(response.data));
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
        navigate("/todo");
    };

    return (
        <div>
            <h2>Add New To-Do</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Task:</label>
                    <input name="task" type="text" />
                </div>
                <button type="submit">Add To-Do</button>
            </form>
        </div>
    );
}

export default AddToDo;
