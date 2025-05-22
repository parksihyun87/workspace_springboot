import {Link} from "react-router-dom";

export default function AddQuestion(){
    return(
        <>
            계속 추가 하시겠습니까?
            <Link to={"/addcustomer/detailedadduser"}>예</Link>&nbsp;
            <Link to={"/"}>아니오</Link>
        </>
    )
}

