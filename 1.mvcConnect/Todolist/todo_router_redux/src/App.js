import { Route, Routes } from "react-router-dom";
import "./App.css";
import MainLayout from "./MainLayout";
import ToDoList from "./ToDoList";

function App() {
  return (
    <>
    <Routes>
      <Route path="/" element={<MainLayout/>}>
        <Route index element={<ToDoList/>}></Route>
        <Route path ="/todolist" element={<ToDoList/>}></Route>
      </Route>
    </Routes>
    </>
  );
  
}
export default App;
