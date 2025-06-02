import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    productList : [
        { id: 1, title : "검정치마", price : 1000, imagesrc:"https://dummyimage.com/200x200/00f/fff.jpg&text=product"},
        { id: 2, title : "모자", price : 2000, imagesrc:"https://dummyimage.com/200x200/00f/fff.jpg&text=product"},
        { id: 3, title : "신발", price : 1500, imagesrc:"https://dummyimage.com/200x200/00f/fff.jpg&text=product"},
    ],
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