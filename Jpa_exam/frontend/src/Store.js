import JpaUserSlice from "./JpaUserSlice";
import {configureStore} from "@reduxjs/toolkit";


const store=configureStore({
    reducer:{
        user:JpaUserSlice.reducer}
});

export default store;
// 스토어는 리듀서를 가져와서 실행해준다.