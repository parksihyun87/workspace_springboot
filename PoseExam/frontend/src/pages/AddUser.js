import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import apiClient from "../api/ApiInstance";
import {setToken, userLogin} from "../store";

export default function AddUser() {
    const dispatch= useDispatch();
    const navigate = useNavigate();
    // const logStatus= useSelector(state=>state.post.flagLogout);

    const handleJoin = async (e)=>{
        try {
            e.preventDefault();
            const response = await apiClient.post("/join",
                {
                    username:e.target.username.value,
                    password:e.target.password.value,
                    writer: e.target.writer.value
                }
            );
            console.log(response.data);
            navigate("/");
        } catch(error){
            console.log(error);
        }
    }
    return (
        <div>
            <form onSubmit={handleJoin}>
                <label>아이디: </label><input type={"text"} name={"username"}/>
                <label>비밀번호: </label><input type={"password"} name={"password"}/>
                <label>필명: </label><input type={"text"} name={"writer"}/>
                <button type={"submit"}>회원가입</button>
            </form>
        </div>
    );
}
