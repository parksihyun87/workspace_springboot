import {Outlet, useNavigate} from "react-router-dom";

export default function ConditionSelect() {
    const navigate=useNavigate();
    return (
        <>
            컨셀입니다.
            <form onSubmit={(e)=> {
                e.preventDefault();
                const islocate=e.target.locate.checked;
                const isbirthdate=e.target.birthdate.checked;

                if(isbirthdate && islocate) {
                    navigate("/conditionselect/detailedcondition/3")
                }else if(islocate){
                    navigate("/conditionselect/detailedcondition/1");
                }
                else if(isbirthdate){
                    navigate("/conditionselect/detailedcondition/2");
                }
                 else {
                    navigate("/conditionselect/detailedcondition/4");
                }
            }}>
                <label>지역검색</label>
                <input name="locate" type={"checkbox"}/>
                <label>생년월일검색</label>
                <input name="birthdate" type={"checkbox"}/>
                <button type={"submit"}>상세검색 입력창</button>
            </form>
            <Outlet/>
        </>
    );
}
