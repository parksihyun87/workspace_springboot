import {configureStore} from "@reduxjs/toolkit";
import toDoReducer from './toDoSlice';

const store=configureStore({
    reducer:{
        toDoList : toDoReducer,
    }
});

export default store;
