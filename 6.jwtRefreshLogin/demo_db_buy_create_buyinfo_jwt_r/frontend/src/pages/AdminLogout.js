import {useEffect, useState} from "react";
import apiClient from "../api/axiosInstance";
import {useDispatch} from "react-redux";
import {adminLogout, setToken, userLogout, userLogStatus} from "../store";
import {useNavigate} from "react-router-dom";

export default function AdminLogout(){
    const[message,setMessage]=useState(null);
    const dispatch= useDispatch();
    const navigate= useNavigate();

    useEffect(() => {
        const fetchdata= async ()=>{
            try {
                await dispatch(setToken(null));
                await dispatch(adminLogout());
                const response= await apiClient.delete("http://localhost:8080/refresh-cookie");
                console.log(response.data);
                dispatch(userLogStatus(Date.now()));
                navigate("/");
            } catch (error){
                console.log(error);
            }
        }
        fetchdata();
    }, []);
    return (
        <>
            <div>
            {message}
            </div>
        </>
    );
}