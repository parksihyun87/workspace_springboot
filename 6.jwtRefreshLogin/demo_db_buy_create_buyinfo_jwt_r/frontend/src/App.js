import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import MainLayout from "./pages/MainLayout";
import Home from "./pages/Home";
import DetailCondition from "./pages/DetailCondition";
import ConditionSelect from "./pages/ConditionSelect";
import DisplayUserInfo from "./pages/DisplayUserInfo";
import DisplayBuyInfo from "./pages/DisplayBuyInfo";
import CreateUserInfo from "./pages/CreateUserInfo";
import JoinUser from "./pages/JoinUser";
import AddBuyInfo from "./pages/AddBuyInfo";
import JoinUserResult from "./pages/JoinUserResult";
import AddBuyInfoResult from "./pages/AddBuyInfoResult";
import AdminLogin from "./pages/AdminLogin";
import AdminAdd from "./pages/AdminAdd";
import AdminLogout from "./pages/AdminLogout";
import UserLogin from "./pages/UserLogin";
import UserLogout from "./pages/UserLogout";

function App() {
    return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainLayout/>}>
            <Route index element={<Home/>}/>
            <Route path={"/userlogin"} element={<UserLogin/>}></Route>
            <Route path={"/userlogout"} element={<UserLogout/>}></Route>
            <Route path={"/adminlogin"} element={<AdminLogin/>}></Route>
            <Route path={"/adminlogout"} element={<AdminLogout/>}></Route>
            <Route path={"/adminadd"} element={<AdminAdd/>}></Route>
            <Route path="/search" element={<ConditionSelect/>}>
              <Route path="/search/detail-condition" element={<DetailCondition/>}/>
            </Route>
            <Route path="/display-userinfo" element={<DisplayUserInfo/>}></Route>
            <Route path="/display-buyinfo/:userid" element={<DisplayBuyInfo/>}></Route>
            <Route path="/create-userinfo" element={<CreateUserInfo/>}>
                <Route path="/create-userinfo/join" element={<JoinUser/>}></Route>
                <Route path="/create-userinfo/add-buyinfo" element={<AddBuyInfo/>}></Route>
                <Route path={"/create-userinfo/join/result"} element={<JoinUserResult/>} ></Route>
                <Route path={"/create-userinfo/add-buyinfo/result"} element={<AddBuyInfoResult/>}></Route>
            </Route>
          </Route>
        </Routes>
      </BrowserRouter>
  );
}

export default App;
