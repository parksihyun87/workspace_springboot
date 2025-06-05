import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import ManinLayout from "./pages/ManinLayout";
import UserLogin from "./pages/UserLogin";
import UserLogout from "./pages/UserLogout";
import AddUser from "./pages/AddUser";
import BoardList from "./pages/BoardList";
import ReadPage from "./pages/ReadPage";

function App() {
  return (
        <BrowserRouter>
            <Routes>
                <Route path={"/"} element={<ManinLayout/>}>
                    <Route index element={<Home/>}></Route>
                    <Route path={"/userlogin"} element={<UserLogin/>}></Route>
                    <Route path={"/userlogout"} element={<UserLogout/>}></Route>
                    <Route path={"/join"} element={<AddUser/>}></Route>
                    <Route path={"/boardlist"} element={<BoardList/>}></Route>
                    <Route path={"/boardlist/:pid"} element={<ReadPage/>}></Route>
                </Route>
            </Routes>
        </BrowserRouter>
  );
}

export default App;
