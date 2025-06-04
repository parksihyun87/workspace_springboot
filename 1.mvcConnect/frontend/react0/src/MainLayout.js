import { Outlet } from "react-router-dom";
import Nevigate from "./Navigate";

export default function MainLayout(){
    return (
        <>
        <h1>TEST STORE</h1>
        <Nevigate></Nevigate>
        <Outlet></Outlet>
        </>
    );
}