import {createSlice, configureStore} from "@reduxjs/toolkit";

const userInfoSlice=createSlice({
    name:"userInfo",
    initialState:{
        userInfoList:[],
        count:0,
        token:null,
        user:null,
        admin:null,
        flagLogStatus:null
    },
    reducers:{
        addUserInfo:(state, action)=>{
            state.userInfoList.push(action.payload);
            state.count++;
        },
        setUerInfoList:(state, action)=>{
          state.userInfoList=action.payload;
          state.count=action.payload.length;
        },
        clearUserInfo:(state)=>{
            state.userInfoList=[];
            state.count=0;
        },
        setToken:(state,action)=>{
            state.token=action.payload;
        },
        adminLogin:(state,action)=>{
            state.admin=action.payload;
        },
        adminLogout:(state)=>{
            state.admin=null;
        },
        userLogin:(state,action)=>{
            state.user=action.payload;
        },
        userLogout:(state)=>{
            state.user=null;
        },
        userLogStatus:(state,action)=>{
            state.flagLogStatus=action.payload;
        }
    }
});

const store=configureStore({
    reducer:{
        userInfo:userInfoSlice.reducer,
    }
});

export const {addUserInfo,clearUserInfo, setUerInfoList,setToken,adminLogin, adminLogout,userLogin,userLogout,userLogStatus}=userInfoSlice.actions;export default store;