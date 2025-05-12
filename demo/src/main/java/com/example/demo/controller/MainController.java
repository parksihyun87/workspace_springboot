package com.example.demo.controller;

import com.example.demo.data.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MainController {
    @GetMapping(value="/hello")
    public String hello(){
        return "hello world";
    }
//
    @GetMapping(value="/variable/{var}")
    public String variable(@PathVariable String var){
        return var;
    }
    //파라미터 만드는 것임. api함수 안에서 패스 배리어블 어노테이션을 써서 이 유알엘의 내용중에
    // var변수로 들어오는 것을 var안에 넣어주겠다는 뜻임. variable은 변수 경로를 따라서 variable들어간거임
    // 자동으로 매핑이 되는 것임.
    @GetMapping(value = "/request")
    public String request(@RequestParam String name,
                          @RequestParam Integer age,
                          @RequestParam String email){
        return name + " " + age+ " " + email;
    }
    @GetMapping(value = "/request2")
    public StringBuffer request2(@RequestParam Map<String,String> map){
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            sb.append(entry.getKey() + " " + entry.getValue() + "<br>");
        }
        return sb;
    }
    @GetMapping(value = "/request3")
    public String request3(MemberDTO memberDTO){
        return  memberDTO.toString();
    }
}
