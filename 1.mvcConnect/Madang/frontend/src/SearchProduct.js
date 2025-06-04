import {Link, Outlet, useNavigate} from "react-router-dom";
import {useState} from "react";


export default function SearchProduct(){
    const [outletFlag,setoutletFlag]=useState(false);
    const [pickedCriteria,setpickedCriteria]=useState(null);
    const [pickedDate,setpickedDate]=useState(null);
    const navigate=useNavigate();
    const handleSubmit=(e)=>{
        e.preventDefault();
        setoutletFlag(true);
        setpickedCriteria(e.target.r1.value);
        setpickedDate(e.target.date.value);
        navigate("/searchproduct/searchlist");
    };
    return(
        <>
            <div>
                <form onSubmit={handleSubmit}>
                    <label>이전</label>
                    <input type={"radio"} name={"r1"} value="before"/>
                    <label>이후</label>
                    <input type={"radio"} name={"r1"} value="after"/>
                    <input type={"date"} name="date"/>
                    <button type={"submit"} >조건 선택</button>
                </form>
            </div>
            <Outlet context={{outletFlag,pickedCriteria,pickedDate}}></Outlet>
        </>
    )
}