import {useNavigate, useParams} from "react-router-dom";
import {use} from "react";
import {useDispatch} from "react-redux";
import apiClient from "./api/clientInstance";
import {addUser} from "./JpaUserSlice";

export default function DetailedCondition() {
    const {params}= useParams();
    const dispatch=useDispatch();
    const navigate= useNavigate();
    let content=null;

    switch (Number(params)){
        case 1:
            content =
                <>
                    <form onSubmit={async (e)=>{
                        e.preventDefault();
                        try{
                            const p=e.target.locate.value;
                            const response= await apiClient.get(`userinfo/addr/${p}`)
                            dispatch(addUser(response.data));
                        } catch (error){
                            console.log(error);
                        }
                        navigate("/displayuserinfo");
                    }
                    }>
                        <label>지역검색</label>
                        <input type={"text"} name={"locate"}/>
                        <button type={"submit"}>검색</button>
                    </form>
                </>
            break;
        case 2:
            content =
                <>
                    <form onSubmit={async (e)=>{
                        e.preventDefault();
                        try{
                            const p=e.target.birthyear.value;
                            const response= await apiClient.get(`/userinfo/birthyear/${Number(p)}`)
                            dispatch(addUser(response.data));
                        } catch (error){
                            console.log(error);
                        }
                        navigate("/displayuserinfo");
                    }
                    }>
                        <label>연도검색</label>
                        <input type={"text"} name={"birthyear"}/>
                        <button type={"submit"}>검색</button>
                    </form>
                </>
            break;
        case 3:
            content =
            <>
            <form onSubmit={async (e)=>{
                e.preventDefault();
                try{

                    const p=e.target.locate.value;
                    const p2=e.target.birthyear.value;
                    const response= await apiClient.get(`userinfo/addr-birthyear?addr=${p}&birthyear=${p2}`)
                    dispatch(addUser(response.data));
                } catch (error){
                    console.log(error);
                }
                navigate("/displayuserinfo");
            }
            }>

                <label>지역검색</label>
                <input type={"text"} name={"locate"}/>
                <label>생년검색</label>
                <input type={"text"} name={"birthyear"}/>
                <button type={"submit"}>검색</button>
            </form>
            </>
            break;
        case 4:
            content="아무것도 선택하지 않음"
            break;
    }

    return (
        <>
            디테일컨션입니다.
            {content}
        </>
    );
}
