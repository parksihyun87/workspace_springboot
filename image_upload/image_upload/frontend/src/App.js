import './App.css';
import {useState} from "react";
import axios from 'axios';

function App() {
        const [file, setFile] = useState(null);

        const handleFileChange = (e) => {
            setFile(e.target.files[0]);
            // files: 선택된 파일객체(File)들의 배열
            // 여러개의 파일을 선택할수도 있으므로 배열로 저장됨
        };

        const uploadImage = async () => {
            if (!file) return alert("파일을 선택해주세요.");

            const formData = new FormData(); // 멀티미디어 데이터를 요청의 body에 넣을때 사용되는 틀
            formData.append('image', file);
            //JavaScript의 File 객체가 HTTP 요청의 Body에 실제 파일 데이터(binary)로 포함

            try {
                const response = await axios.post('http://localhost:8080/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
                alert("업로드 성공! 이미지 경로: " + response.data.path);
            } catch (err) {
                console.error(err);
                alert("업로드 실패");
            }
        };

        return (
            <div>
                <input type="file" accept="image/*" onChange={handleFileChange}/>
                {/*multiple 속성을 추가하면 여러개의 파일을 선택할수 있음*/}
                <button onClick={uploadImage}>업로드</button>
            </div>

        );
    }

    export default App;
