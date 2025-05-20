import { useDispatch, useSelector } from "react-redux";
import {addToDo, deleteToDo, removeToDo, updateToDo} from "./todoSlice";
import axios from "axios";
import apiClient from "./api/apiInstance";

export default function ToDoList(){
    const dispatch = useDispatch();
    const todos = useSelector((state) => state.todos.items);
    console.log("Todos in ToDoList:", todos);//디버깅.

    const handleComplete= async (id)=>{
        const p={id:id}
        try{
            const response= await apiClient.put("/updatetodolist",p);
            dispatch(updateToDo(id));
        }catch (error) {
            console.log(error);
        }
    }

    const deleteUpdate= async (e)=>{
        try{
            const response= await apiClient.delete("/deletetodolist");
            await dispatch(deleteToDo());
            const response2= await apiClient.get("/todolist");
            response2.data.map((e)=>dispatch(addToDo(e)));
        } catch (error){
            console.log(error);
        }
    }

    return (
        <>
         <ul>
        {todos.map(todo=>(
          <li key={todo.id}>
              <span style={{ textDecoration: todo.completed ? 'line-through' : 'none' }}>
            {todo.task}
              </span>
              <button onClick={() => handleComplete(todo.id)}>완료</button>
          </li>
        ))}
      </ul>
        <button onClick={deleteUpdate}> 완료 삭제</button>
        </>
    );
}