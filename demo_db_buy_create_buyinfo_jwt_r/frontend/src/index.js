import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {persistor, store} from "./store";
import {Provider} from "react-redux";
import {PersistGate} from "redux-persist/integration/react";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Provider store={store}>
    <PersistGate persistor={persistor}>
        <App />
    </PersistGate>
 </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
// 하위에 모두 스토어를 제공한다.
// 퍼시스트게이트의 퍼시스터가 통합확장하는 기능 매니저를 전체에 넘겨준다. 저장하고 삭제하고 하는데 얘를 거쳐서 일이 벌어짐.