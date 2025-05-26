import {createSlice} from "@reduxjs/toolkit";

const MadangSlice= createSlice({
    name:"madang",
    initialState:{prodList:[]},
    reducers:{
        addList:(state,action)=>{
            state.prodList=[];
            state.prodList=action.payload;
        },
        clearList:(state,action)=>{
            state.prodList=[];
        }
    }
});

export const{addList,clearList}=MadangSlice.actions;//1.액션생성자를 각 컴퍼넌트에
export default MadangSlice;
//크리에이트들은 (소,중)2가지로 시작. 안에도 (소,중) 2개, 2개 이다.
//리듀서는 (이,:,(스,액)) 4가지로 시작해 화살표 함수
// 분해할당은 (변,중) 2가지 필요.
// 슬라이스는 익디슬(eds) 3가지임
