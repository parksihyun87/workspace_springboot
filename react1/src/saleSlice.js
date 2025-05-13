import { createSlice } from "@reduxjs/toolkit";


const saleSlice=createSlice({
    name: "sale",
    initialState: {
        arr:[
            {id:1, title: "검정치마", price:1000},
            {id:2, title: "빨간치마", price:2000},
            {id:3, title: "파란치마", price:3000}
        ]
    },
    //리듀서: 액션.페이로드는 받아서 오는 값, 
    // 스테이트는 자신의 스테이트한 값 말함. 
    // 액션은 말 그대로 취할 액션이며, 타입값으로 어떤 것인지 정해줌. 액션. 페이로드는 받아 올 것들. 
    reducers: {
        onAdd: (state,action)=>{
                state.arr.push({id:state.arr.length+1,title:action.payload.title,price:action.payload.price});
            }
        ,

        onModify: (state, action)=>{
            for(let m of state.arr){
                if(Number(action.payload.id)===m.id){
                    m.price=action.payload.price;
                    break;
                }
            }
        },
        onDelete:(state,action)=>{
            state.arr=state.arr.filter((e)=>(Number(action.payload)!==e.id));
            // 삭제 개념은 전 배열에서 필터로 없애서 다시 리로딩 한다는 뜻이엇나? 마따. 배열을 리로딩.
        }
    }
});

export const {onModify,onDelete,onAdd}=saleSlice.actions;// 이게 온모디파이 수출한것.
export default saleSlice;