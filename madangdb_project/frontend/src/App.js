import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainLayout from "./page/MainLayout";
import Nodata from "./page/Nodata";
import SelectOrderInfo from "./page/SelectOrderInfo";
import DisplayOrderInfo from "./page/DisplayOrderInfo";
import DisplayBookInfo from "./page/DisplayBookInfo";
import DisplayCustInfo from "./page/DisplayCustInfo";
import DisplayBookOrderInfo from "./page/DisplayBookOrderInfo";
import Home from "./page/Home";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path={"/"} element={<MainLayout/>}>
            <Route index element={<Home/>}></Route>
            <Route path={"/orderinfo"} element={<SelectOrderInfo/>}>
              <Route path={"/orderinfo/nodata"} element={<Nodata/>}></Route>
              <Route path={"/orderinfo/data"} element={<DisplayOrderInfo/>}></Route>
              <Route path={"/orderinfo/bookinfo/:bookid"} element={<DisplayBookInfo/>}></Route>
              <Route path={"/orderinfo/bookinfo/bookorderinfo"} element={<DisplayBookOrderInfo/>}></Route>
              <Route path={"/orderinfo/custinfo/:custid"} element={<DisplayCustInfo/>}></Route>
            </Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
