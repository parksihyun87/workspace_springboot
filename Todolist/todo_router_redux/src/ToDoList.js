import { useDispatch, useSelector } from "react-redux";
import {addToDo, removeToDo} from "./todoSlice";
import axios from "axios";
import apiClient from "./api/apiInstance";

export default function ToDoList(){
    const dispatch = useDispatch();
    const todos = useSelector((state) => state.todos.items);

    console.log("Todos in ToDoList:", todos);//디버깅.

    return (
        <>
         <ul>
        {todos.map(todo=>(
          <li key={todo.id}>
            {todo.task}
            <button onClick={()=>dispatch(removeToDo(todo.id))}>Delete</button>
          </li>
        ))}
      </ul>
        </>
    );
}