import { useDispatch, useSelector } from "react-redux";
import {addToDo, removeToDo, updateToDo} from "./todoSlice";
import axios from "axios";
import apiClient from "./api/apiInstance";

export default function ToDoList(){
    const dispatch = useDispatch();
    const todos = useSelector((state) => state.todos.items);

    console.log("Todos in ToDoList:", todos);//디버깅.
    const handleUpdate= async (id)=>{
        const p={id:id}
        try{
            const response= apiClient.put("/updatetodolist",p);
            dispatch(updateToDo(id));
        }catch (error) {
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
              <button onClick={() => handleUpdate(todo.id)}>완료</button>
          </li>
        ))}
      </ul>
        </>
    );
}