import { Link } from "react-router-dom";

export default function Nevigate(){
    return (
        <>
        <p>
            <Link to = "/">LIST</Link> |
            <Link to = "/new-product">NEW_PRODUCT</Link> |
        </p>
        </>
    );
}