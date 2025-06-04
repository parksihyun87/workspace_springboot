import {createSlice} from "@reduxjs/toolkit";

const JpaUserSlice= createSlice({// (콘스트, 슬라이스 이름, 는 ,크리에이트 슬라이스,소,중)
    name:"user",
    initialState:{userList:[],
        buyList:[]},
    reducers:{
        addUser:(state,action)=>{
            state.userList=[];
            state.userList=action.payload;
        },
        clearUser:(state)=>{
            state.userList=[];
        },
        addBuy:(state,action)=>{
            state.buyList=[];
            state.buyList=action.payload;
        },
        clearBuy:(state)=>{
            state.buyList=[];
        }
    }
});

export const{addUser,addBuy,clearBuy,clearUser}=JpaUserSlice.actions;
export default JpaUserSlice;

//익스포트는 (액션생성자들, 리듀서) 두가지를 수출한다.
