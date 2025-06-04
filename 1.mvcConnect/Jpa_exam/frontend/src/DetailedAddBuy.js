
export default function DetailedAddBuy() {
    
    return (
        <>
            <div>
            디에드바이
                <form onSubmit={handlesubmit}>&nbsp;
                    <div><label>고객아이디</label>&nbsp;&nbsp;&nbsp;<input name={"userid"} ref={useridRef} type={"text"}/>&nbsp;&nbsp;&nbsp;<button type={"button"} onClick={handlechoice}> 아이디 선택(비활성화)</button></div>
                    <div><label>제품이름</label>&nbsp;&nbsp;&nbsp;<input name={"username"} type={"text"}/></div>
                    <div><label>그룹이름</label>&nbsp;&nbsp;&nbsp;<input name={"phone"} type={"text"}/></div>
                    <div><label>가격</label>&nbsp;&nbsp;&nbsp;<input name={"birthyear"} type={"text"}/></div>
                    <div><label>구매개수</label>&nbsp;&nbsp;&nbsp;<input name={"height"} type={"text"}/></div>
                    <button type={"submit"}>입력완료</button>
                </form>
            </div>
        </>
    );
}