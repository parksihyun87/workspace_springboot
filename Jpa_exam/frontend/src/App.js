import logo from './logo.svg';
import './App.css';
import {Route, Routes} from "react-router-dom";
import Header from "./Header";
import Home from "./Home";
import ConditionSelect from "./ConditionSelect";
import DetailedCondition from "./DetailedCondition";
import DisplayUserInfo from "./DisplayUserInfo";

function App() {
  return (
      <>
        <Routes>
            <Route path="/" element={<Header/>}>
                <Route index element={<Home/>}></Route>
                <Route path="/home" element={<Home/>}></Route>
                <Route path="/conditionselect" element={<ConditionSelect/>}>
                    <Route path="/conditionselect/detailedcondition/:params" element={<DetailedCondition/>}></Route>
                </Route>
                <Route path="/displayuserinfo" element={<DisplayUserInfo/>}></Route>
            </Route>
        </Routes>
      </>
  );
}
export default App;
