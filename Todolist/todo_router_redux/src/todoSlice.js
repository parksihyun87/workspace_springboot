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
        },
        updateToDo: (state, action) => {
            state.items = state.items.map((e) => {
                if (e.id === action.payload) {
                    return { ...e, completed: true }; // 새로운 객체 반환
                }
                return e;
            });
        },
        deleteToDo:(state, action)=>{
            state.items=[];
        }
    }
});

export const {addToDo, removeToDo,updateToDo,deleteToDo}=todoSlice.actions;
export default todoSlice;
