import { configureStore } from "@reduxjs/toolkit";
import saleSlice from "./saleSlice";


const store = configureStore({
    reducer: {
    sale: saleSlice.reducer
    }
});

export default store;