import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import {logoutStatus, setPostList, setToken, userLogin} from "../store";
import {useEffect} from "react";

export default function UserLogout() {
    const dispatch= useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        const doLogout=async ()=>{
            await dispatch(setToken(null));
            await dispatch(userLogin(null));
            await dispatch(setPostList(null));
            await dispatch(logoutStatus(Date.now()));
            navigate("/");
        }
        doLogout();
    }, []);

    return (
        <div>
        </div>
    );
}
