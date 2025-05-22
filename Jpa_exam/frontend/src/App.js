import logo from './logo.svg';
import './App.css';
import {Route, Routes} from "react-router-dom";
import Header from "./Header";
import Home from "./Home";
import ConditionSelect from "./ConditionSelect";
import DetailedCondition from "./DetailedCondition";
import DisplayUserInfo from "./DisplayUserInfo";
import BuyCondition from "./BuyCondition";
import AddCustomer from "./AddCustomer";
import DetailedAddUser from "./DetailedAddUser";
import DetailedAddBuy from "./DetailedAddBuy";
import AddQuestion from "./api/AddQuestion";

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
                <Route path="/displayuserinfo/buycondition/:userid" element={<BuyCondition/>}></Route>
                <Route path="/addcustomer" element={<AddCustomer/>}>
                    <Route path="/addcustomer/detailedadduser" element={<DetailedAddUser/>}>
                        <Route path="/addcustomer/detailedadduser/addquestion" element={<AddQuestion/>}></Route>
                    </Route>
                    <Route path="/addcustomer/detailedaddbuy" element={<DetailedAddBuy/>}></Route>
                </Route>

            </Route>
        </Routes>
      </>
  );
}
export default App;


//path="/addcustomer/detailedadduser/addquesiton"