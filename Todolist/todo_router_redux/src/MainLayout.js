
import Header from "./Header";
import { Outlet } from "react-router-dom";
import {useEffect} from "react";
import apiClient from "./api/apiInstance";
import {addToDo} from "./todoSlice";
import {useDispatch} from "react-redux";

export default function MainLayout(){
    const dispatch = useDispatch();
    useEffect(() => {
        const fetchData = async ()=>{
            try {
                const response = await apiClient.get("/todolist");
                response.data.map((e)=>dispatch(addToDo(e)));
            } catch (error) {
                console.log(error);
            }
        }
        fetchData();
    }, []);
 
  return (
    <>
      <Header></Header>     
      <Outlet/> 
    </>
  );
}