import logo from './logo.svg';
import './App.css';
import {Route, Routes} from "react-router-dom";
import Header from "./Header";
import SearchProduct from "./SearchProduct";
import Home from "./Home";
import SearchList from "./SearchList";
import DetailedOrdDisplay from "./DetailedOrdDisplay";
import ProdIdDisplay from "./ProdIdDisplay";

function App() {
  return (
      <>
        <Routes>
            <Route path="/" element={<Header/>}>
                <Route index element={<Home/>}></Route>
                <Route path="/searchproduct" element={<SearchProduct/>}>
                    <Route path="/searchproduct/searchlist" element={<SearchList/>}></Route>
                    <Route path="/searchproduct/searchlist/:prodid" element={<ProdIdDisplay/>}></Route>
                    <Route path="/searchproduct/searchlist/detailedorder" element={<DetailedOrdDisplay/>}></Route>
                </Route>
            </Route>
        </Routes>
      </>
  );
}

export default App;
