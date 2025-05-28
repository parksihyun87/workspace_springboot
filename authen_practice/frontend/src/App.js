import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {
  return (
      <BrowserRouter>
      <Routes>
        <Route path={"/"} element={"<MainLayout/>"}>
          {/*<Route paht={"/index"} element={??}*/}
        </Route>
      </Routes>
      </BrowserRouter>

  );
}

export default App;
