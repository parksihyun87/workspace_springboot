import {useSelector} from "react-redux";

export default function DisplayUserInfo() {
    let selector=useSelector(state=>state.user.userList);

    return (
        <>
            디스플유저인포입니다.
            <div></div>
            --------------------------
            <div></div>
            {selector.map((data)=>(
                    <>
                    <div>{data.userid}</div>
                    <div>{data.username}</div>
                    <div>{data.birthyear}</div>
                    <div>{data.addr}</div>
                    <div>{data.mobile1}</div>
                    <div>{data.mobile2}</div>
                    <div>{data.height}</div>
                    <div>{data.mdate}</div>
                    --------------------------
                    </>
            ))}
        </>
    );
}
