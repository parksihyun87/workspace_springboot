import { createSlice } from "@reduxjs/toolkit";

const todoSlice=createSlice({
    name:"todos",
    initialState:{
        items:[], //{id:.., task:...}        
    },
    reducers:{
        addToDo:(state, action)=>{
            state.items.push(action.payload);//{id:action.payload, task:action.payload}
        },
        removeToDo:(state, action)=>{
            state.items=state.items.filter(t=>t.id!==action.payload);
        }
    }
});

export const {addToDo, removeToDo}=todoSlice.actions;
export default todoSlice;
