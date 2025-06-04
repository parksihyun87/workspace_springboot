import apiClient from "../api/apiinstance";
import errorDisplay from "../util/errorDisplay";
import {useDispatch, useSelector} from "react-redux";
import {setOrderdList} from "../store";
import {Outlet, useNavigate} from "react-router-dom";


export default function SelectOrderInfo(){
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleSubmit=async (e)=>{
        e.preventDefault();
        let path = null;
        if(e.target.condition[0].checked){
            path="before";
        }else if(e.target.condition[1].checked){
            path="after";
        }
        if(path === null ){
            alert("이전/이후를 선택하세요");
        }else{
            try{
                const response = await apiClient.get("/orderinfo/"+path, {
                    params:{
                        date:e.target.date.value,
                    }
                });
                if(response.status===250){
                    dispatch(setOrderdList([]));
                }else{
                    dispatch(setOrderdList(response.data));
                    navigate("/orderinfo/data");
                }
            }catch (error)
                errorDisplay(error);
            }
        }

    }
    return (
        <>
            <form onSubmit={handleSubmit}>
                <label htmlFor="before">이전</label>
                <input id="before" type="radio" name="condition"/>
                <label htmlFor="after">이후</label>
                <input id="after" type="radio" name="condition"/>
                <input type="date" name="date" required/>
                <button type="submit">조건선택</button>
            </form>
            <hr/>
            <Outlet></Outlet>


        </>
    );
}