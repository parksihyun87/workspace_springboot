import logo from './logo.svg';
import './App.css';
import {Swiper, SwiperSlide} from "swiper/react";
import {Autoplay, Navigation, Pagination} from "swiper/modules";
//css도 일부 필요하다
import 'swiper/css'
import 'swiper/css/navigation'
import 'swiper/css/pagination'

function App() {
  return (
      <>
          <Swiper style={{width:"400px", height:"300px"}}// 오토플레이는 시간에 맞춰서 로테이션 도는 역할, 페이지네이션은 ... 시간안되도 눌르면 바뀜, 네비게이션은 양옆 화살표 눌르면  바뀌는 것
          modules={[Autoplay, Pagination, Navigation]}
                  spaceBetween={30}
                  slidesPerView={1}
                  loop={true}// 계속 처음으로 돌아가는것
              autoplay={{
                  delay:3000,//3초
                  disableOnInteraction:false,//트루로 만들면 페이지네이션등으로 손한번대면 멈춤
              }}
                  pagination={{clickable:true}}//클릭이 가능하다
                  navigation={true}//이동
          >
              <SwiperSlide style={{
                  display:"flex",
                  justifyContent:"center",
                  alignItems:"center"
              }}><img src={"/media/media_animal/lama1.png"} style={{width:"500px", height:"400px"}}/></SwiperSlide>
              <SwiperSlide style={{
                  display:"flex",
                  justifyContent:"center",
                  alignItems:"center"
              }}><img src={"/media/media_animal/lama2.png"} style={{width:"500px", height:"400px"}}/></SwiperSlide>
              <SwiperSlide style={{
                  display:"flex",
                  justifyContent:"center",
                  alignItems:"center"
              }}><img src={"/media/media_animal/lama3.png"} style={{width:"500px", height:"400px"}}/></SwiperSlide>
          </Swiper>
      </>
  );
}

export default App;
// 맨앞의 슬래시는 항상 퍼블릭을 위치함