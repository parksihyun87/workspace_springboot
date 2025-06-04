import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import RefExample from './RefExample';
import DelayedMessage from './DelayedMessage';
import TimerCounter from './TimerCounter';
import ExampleComponent from './ExampleComponent';
import ExampleComponent2 from './ExmapleComponent2';
import AutoFocusInput from './AutoFocusInput';
import CounterComponent from './CounterComponent';
import TimerComponent from './TimerComponent';

function App() {
  const[content, setContent]= useState(null);
  useEffect(()=>{
     fetch("http://localhost:8080/userlist")
  .then(response => {
    if(!response.ok){
      throw new Error("네트워크 오류");
    }
    return response.json();
  })
  .then(data=>{
    setContent(data.map(t=><div key={t.userid}>{t.username}<br/></div>))
  })
  .catch(error=>{
    console.log(error);
  });
  // 응답이 안 온 상태임. 그래서 비 스테이트면 안됨

  },[])
 
  return (
    <>
      {content}
    </>
  );
}

export default App;
// function App() {
//   const [flag, setFlag] = useState(false);

//   return (
//     <>
//     <button on onClick={(e)=>setFlag(!flag)}> 컴퍼넌트 변경</button>
//     {flag && <TimerComponent/>}
//     {/* // <TimerCounter></TimerCounter>} */}
//      {/* <DelayedMessage></DelayedMessage>}//  */}
//       {/* <TextInput></TextInput> */}
//       {/* <RefExample></RefExample> */}
//     </>
//   );
// }

// export default App;
