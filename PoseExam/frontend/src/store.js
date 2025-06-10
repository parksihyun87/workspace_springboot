import {combineReducers, configureStore, createSlice} from "@reduxjs/toolkit";
import storage from "redux-persist/lib/storage"
import {persistReducer, persistStore} from "redux-persist";

const postSlice=createSlice({
    name: "post",
    initialState:{
        postList:[],
        token:null,
        user:null,
        flagLogout:null
    },
    reducers:{
        setPostList:(state,action)=>{
            state.postList=action.payload;
        },
        setToken:(state,action)=>{
            state.token= action.payload;
        },
        userLogin:(state,action)=>{
            state.user= action.payload;
        },
        logoutStatus:(state,action)=>{
            state.flagLogout= action.payload;
        }
    }

});

const persistConfig = {
    key:"root",
    storage,
    whitelist:['post']// 포함할 슬라이스
};

const rootReducer = combineReducers(// 합칠 리듀서
    {
        post: postSlice.reducer //(이름, : , 슬라이스, 리듀서)4가지
    });

export const persistedReducer= persistReducer(persistConfig,rootReducer);// 두개를 묶어서 로컬스토리지 연결

export const store = configureStore({
    reducer:persistedReducer
});

export const persistor= persistStore(store);// 이제 이 객체로 리덕스와 로컬스토리지 관리가 형성

export const{setPostList, setToken, userLogin, logoutStatus} = postSlice.actions;
    // const persistor = persistStore(store)

