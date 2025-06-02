import {Link} from "react-router-dom";

export default function Nodata(){
    return(
        <>
            <h1>검색데이터가 존재하지 않습니다.</h1>
            <Link to={"/orderinfo"}>이전으로</Link>
        </>
    );
}