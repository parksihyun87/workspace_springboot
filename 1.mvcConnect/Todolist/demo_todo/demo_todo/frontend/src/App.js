import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ToDoLayout from './ToDoLayout';
import ToDoList from './ToDoList';
import AddToDo from './AddToDo';

function App() {

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/todo" element={<ToDoLayout />}>
                    <Route index element={<ToDoList />} />
                    <Route path="/todo/add" element={<AddToDo />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
