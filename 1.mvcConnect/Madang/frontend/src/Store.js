import {configureStore} from "@reduxjs/toolkit";
import MadangSlice from "./MadangSlice";

    const store=configureStore({
        reducer:{
            madang: MadangSlice.reducer
        }//(이,:,슬,리)4가지
    });

    export default store;
