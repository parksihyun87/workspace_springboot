import {createSlice} from "@reduxjs/toolkit";

const initialState={
    toDoList:[],
}
const toDoSlice=createSlice({
    name:"toDoList",
    initialState:initialState,
    reducers:{
        addToDoList:(state, action)=>{
            state.toDoList.push(action.payload);
        },
        completedToDoList:(state, action)=>{
            state.toDoList=state.toDoList.map(t=>t.id===action.payload.id? action.payload:t);
        },

        deleteToDoList:(state, action)=>{
            state.toDoList=[];
        }
    }
});

export const {addToDoList, completedToDoList, deleteToDoList}=toDoSlice.actions;
export default toDoSlice.reducer;