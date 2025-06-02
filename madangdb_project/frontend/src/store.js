import {createSlice, configureStore} from "@reduxjs/toolkit";

const madandDBInfoSlice=createSlice({
    name:"madangDBInfo",
    initialState:{
        bookInfo:null,
        customerInfo: null,
        orderdList:[],
    },
    reducers:{
        setBookInfo:(state, action)=>{
            state.bookInfo=action.payload;
        },

        setCustomerInfo:(state, action)=>{
            state.customerInfo=action.payload;
        },

        setOrderdList:(state, action)=>{
            state.orderdList=action.payload;
        }
        // clearBookList:(state)=>{
        //     state.bookList=[];
        // }

    }
});

const store=configureStore({
    reducer:{
        madangDBInfo:madandDBInfoSlice.reducer,
    }
});

export const {setBookInfo,setCustomerInfo,setOrderdList}=madandDBInfoSlice.actions;
export default store;