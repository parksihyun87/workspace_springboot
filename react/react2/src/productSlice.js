import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    productList : [],
    count:0,
};

const productSlice = createSlice({
    name:"product",
    initialState : initialState,
    reducers : {
        productAdd:(state, action)=>{
            state.productList.push(action.payload);
            state.count++;
        },

        productUpdate:(state, action)=>{
            state.productList = state.productList.map(t=>t.id === action.payload.id? action.payload : t);
        },

        productDelete:(state, action) =>{
            state.productList = state.productList.filter(t=>t.id !== Number(action.payload))
            state.count--;
        }
    }
});

export const {productAdd, productUpdate, productDelete} = productSlice.actions;
export default productSlice;