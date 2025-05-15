import { Route, Routes, useParams } from "react-router-dom";
import Main from "./Main";
import SalePage from "./SalePage";
import List from "./List";
import UpdateProduct from "./UpdateProduct";
import NewProduct from "./NewProduct";

function App() {
  
  return (
    <>
      <Routes>
        <Route path='/' element={<Main/>}>
          <Route index element={<List/>}></Route>
          <Route path='/detail/:id' element={<SalePage/>}></Route> 
          <Route path='/:params' element={<UpdateProduct/>}></Route> 
          <Route path='/newproduct' element={<NewProduct/>}></Route>
        </Route>
      </Routes>
    </>
  );
}

export default App;
