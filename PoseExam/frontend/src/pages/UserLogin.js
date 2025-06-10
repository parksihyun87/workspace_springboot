import apiClient from "../api/ApiInstance";
import {useDispatch, useSelector} from "react-redux";
import {setToken, userLogin} from "../store";
import {useNavigate} from "react-router-dom";

export default function UserLogin() {
    const dispatch= useDispatch();
    const navigate = useNavigate();
    // const logStatus= useSelector(state=>state.post.flagLogout);

    const handleLogin = async (e)=>{
        try {
            e.preventDefault();
            const response = await apiClient.post("/login",
                new URLSearchParams({
                username:e.target.username.value,
                password:e.target.password.value,
            })
            );
            await dispatch(setToken(response.headers["authorization"]));
            await dispatch(userLogin(response.data.username));
            console.log(response.headers["authorization"]);
            console.log(response.data.username);
            navigate("/");
        } catch(error){
            if(error.response && error.response.status===409){
                console.log(error.response.data);
            }
            console.log(error);
        }
    }

    return (
            <div>
                <form onSubmit={handleLogin}>
                    <label>아이디: </label><input type={"text"} name={"username"}/>
                    <label>비밀번호: </label><input type={"password"} name={"password"}/>
                    <button type={"submit"}>로그인</button>
                </form>
            </div>
    );
}
