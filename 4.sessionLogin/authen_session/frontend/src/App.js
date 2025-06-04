import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainLayout from "./MainLayout";
import Login from "./Login";
import AdminComponent from "./AdminComponent";
import {useEffect} from "react";
import apiClient from "./apiInstance/apiInstance";
import {setToken} from "./store";
import {useDispatch, useSelector} from "react-redux";

function App() {
    const dispatch = useDispatch();
    useEffect(() => {
        const fetchData= async ()=>{
            try{
                const response = await apiClient.get("/csrf-token", {
                    withCredentials:true
                });// 이걸 만들어서 돌려줘야 함.
                dispatch(setToken(response.data['csrf-token']));
                console.log(response.data['csrf-token']);// 맵에서 그냥 token으로 하면 data.token해도 댐.(객체의 속성으로 쓸수 없는 특수문자 하이픈이 들어가서, 원래는 두방법다 되지만[]속성이름이 하이픈으로 들어가면 반드시 []로접근해야 한다.

            } catch(error) {

            }
        }
        fetchData();

    }, []);

  return (
      <>
        <BrowserRouter>
          <Routes>
            <Route path={"/"} element={<MainLayout/>}>
              <Route index element={<Login/>}></Route>
              <Route path={"/admin"} element={<AdminComponent/>}></Route>
            </Route>
          </Routes>
        </BrowserRouter>
      </>
  );
}

export default App;
