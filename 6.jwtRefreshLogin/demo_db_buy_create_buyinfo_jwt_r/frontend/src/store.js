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

const persistConfig={// 스토리지에 뭔가 저장할때 거기도 키와 밸류로 저장을 하고 그 키로 가져온다. 내가 설정한 모든 키를 root로 가져온다.
    key:"root",
    storage,
    whitelist:['userInfo']//배열로 들어감, 여러 슬라이스 중에 내가 저장할 리덕스의 슬라이스// 블랙리스트는 뺄것만 적어주면 나머지를 쓰겠다는 것
};
const rootReducer=combineReducers({//컴바인리듀서안에 중괄호에다가 넣으면, 두개를 합쳐 하나의 리듀서로 만듬. 하나라도 컴바인은 해야 함.
    userInfo:userInfoSlice.reducer
})

export const persistedReducer=persistReducer(persistConfig,rootReducer);// 묶어놓은거를 퍼시스트 컨피그를 바탕으로 만듬. 이제 로컬스토리지랑 연동이 됨.


export const store=configureStore({// 디폴트 안해서 이제 중괄호 필요.
    reducer: persistedReducer,
});// 이거 하나만 넣으면 된다. ->슬라이스들을 합치고 리듀서도 합쳤음. 실제로 행동을 바꿀 수 잇는 객체를 만들어야 함

export const persistor = persistStore(store);// 이게 등록된 객체임. 리덕스도 다루고 로컬스토리지도 다룰 수 있는 객체가 됨. 이걸 퍼게에다가 등록해야 함.

export const {addUserInfo,clearUserInfo, setUerInfoList,setToken,adminLogin, adminLogout,userLogin,userLogout,userLogStatus}=userInfoSlice.actions;