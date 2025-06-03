import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import MainLayout from './MainLayout';
import List from './List';
import DetailProduct from "./DetailProduct";
import NewProduct from "./NewProduct";
import EditProduct from './EditProduct';


function App() {
  return (
      <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainLayout></MainLayout>}>
            <Route index element={<List></List>}></Route>
            <Route path ="/detail-product/:id" element={<DetailProduct></DetailProduct>}></Route>
            <Route path = "/new-product" element={<NewProduct></NewProduct>}></Route>
            <Route path = "/edit-product/:id" element={<EditProduct></EditProduct>}></Route>
          </Route>
        </Routes>
      </BrowserRouter>
   
      </>

  );
}

export default App;
