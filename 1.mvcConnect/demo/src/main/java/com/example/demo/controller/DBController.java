package com.example.demo.controller;

import com.example.demo.data.User;
import com.example.demo.data.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController// 유저를 소유하고 있음 얘도 빈으로 등록되는 애임.
@RequiredArgsConstructor
public class DBController {
    private final UserRepository userRepository;
//    public DBController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    @GetMapping(value = "/userlist")
    public List<User> getUserList() {// 한줄당 한 객체.
        List<User> userList =this.userRepository.findAll();// 레코드가 여러개여어서 배열로 받음. 결과가 리턴됨
        return  userList;
    }

    @GetMapping(value="/userlist/{id}")
    public User getUser(@PathVariable String id){
        Optional<User> user = this.userRepository.findById(id);// id와 같은 id하나를 리턴해 줌. 유저를 리텀해줌. 유저를 한번 래핑한 애를 리턴
        // 유저를 옵셔날로 싼 거임. 옵셔날은 검사과정임. 위의 결과가 없을 수도 있음. 유저를 감싸는 옵셔널 객체를 리턴해줌.
        if(user.isPresent()){//이즈프레젠트 유효한게 있다~ 존재한다면,
            return user.get();//??
        }
        return null;
    }
    @PostMapping(value = "/user")
    public User saveUser(@RequestBody User user){// 포스트 요청 보낼때는(쓰기할때) 쓰는 내용을 바디로 만들어서 json 문자열 객체로 만들어서 전달하면, 자바 객체로 변형시켜서 받아줌.
        return this.userRepository.save(user);// 유저테이블 담당. 세이브는 둘 중하나 함. 업데이트도 하고 인서트도 함. 같은 프라이머리키가 있다면 업데이트를 함. 없다면 인서트를 하는걸로 재정의함// .이걸 다시 제이슨 객체로 만들어서 리턴해줌.
    }

    @DeleteMapping(value = "/user/{var}")// 파라미터, 임의로 내가 값을 넣을 수 있으며,연결과 동시에 이걸로 데이터 관련 조작을 할 수 있음.
    public String deleteUser(@PathVariable String var){
        if(this.userRepository.existsById(var)){
            this.userRepository.deleteById(var);
            return "Deleted Success";
        }
        return "Delete Fail";
    }
}// 클래스가 상속받으면 객체 만듬. 인터페이스로 레포지가 붙어있어서 임플리 해서 알맞게 다 오버라이딩 해놓음 스프링부트가 그렇게
// 클래스 만들어서 빈에 컨테이너에 객체로 만들어 놓음. 기본키랑 ... 외래키 연결되었을때는 안지워져야 함.
