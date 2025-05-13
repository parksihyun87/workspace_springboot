import { Link, Outlet } from "react-router-dom";

export default function Main(){
          
    return(
        <>
            <h1>TEST STORE</h1>
            <Link to ="/"><h2>LIST |</h2></Link>
            <Link to ="/newproduct"><h2>NEW PRODUCT</h2></Link>
            <Outlet/>
        </>
    )
}