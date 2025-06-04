import {createSlice, configureStore, combineReducers} from "@reduxjs/toolkit";
import storage from "redux-persist/lib/storage"
import {persistReducer, persistStore} from "redux-persist";

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

const persistConfig={
    key:"root",
    storage,
    whitelist:['userInfo']//크게(스토리지 저장 최상위 키, 로컬용 스토리지, 리덕스에서 사용할 슬라이스) 이 3가지를 설정해 준다.
};
const rootReducer=combineReducers({//1. 합치기
    userInfo:userInfoSlice.reducer
})

export const persistedReducer= persistReducer(persistConfig,rootReducer);//2. 합친거를 컨피그의 설정도 섞어서 재생성하여 이제 로컬스토리지랑 연동기능 가짐

const store=configureStore({
    reducer: persistedReducer,//3. 이제 리듀서도 퍼시스트리듀서한것으로 적용한다.
});

export const persistor = persistStore(store); // 4. 이제 퍼시스트로 리덕스도 다루고 로컬스토리지도 다룰 수 있는 객체가 됨.

export const {addUserInfo,clearUserInfo, setUerInfoList,setToken,adminLogin, adminLogout,userLogin,userLogout,userLogStatus}=userInfoSlice.actions;