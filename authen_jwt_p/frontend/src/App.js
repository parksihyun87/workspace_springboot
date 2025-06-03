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
