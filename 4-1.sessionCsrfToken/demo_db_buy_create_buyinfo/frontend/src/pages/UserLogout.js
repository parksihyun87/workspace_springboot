import {useEffect, useState} from "react";
import apiClient from "../api/axiosInstance";
import {useDispatch} from "react-redux";
import {adminLogout, userLogout, userLogStatus} from "../store";
import {useNavigate} from "react-router-dom";

export default function UserLogout(){
    const[message,setMessage]=useState(null);
    const dispatch= useDispatch();
    const navigate= useNavigate();

    useEffect(() => {
        const fetchdata= async ()=>{
            try {
                const response = await apiClient.post("/logout");
                await setMessage(response.data);
                dispatch(userLogout());
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