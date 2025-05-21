import {createSlice} from "@reduxjs/toolkit";

const JpaUserSlice= createSlice({// (콘스트, 슬라이스 이름, 는 ,크리에이트 슬라이스,소,중)
    name:"user",
    initialState:{userList:[]},
    reducers:{
        addUser:(state,action)=>{
            state.userList=action.payload;
        }
    }
});

export const{addUser}=JpaUserSlice.actions;
export default JpaUserSlice;

//익스포트는 (액션생성자들, 리듀서) 두가지를 수출한다.
