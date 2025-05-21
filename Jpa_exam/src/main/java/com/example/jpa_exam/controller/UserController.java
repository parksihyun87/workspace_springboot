package com.example.jpa_exam.controller;
import com.example.jpa_exam.data.dto.UserDTO;
import com.example.jpa_exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/userinfo")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "user-list")
    public ResponseEntity<List<UserDTO>> getAllUserList(){
        List<UserDTO> userList =this.userService.getAllUserList();
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value = "addr/{addr}")
    public ResponseEntity<List<UserDTO>> userInfo(@PathVariable("addr") String addr){
        List<UserDTO> userList = this.userService.getUserInfoByAddr(addr);
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value="birthyear/{birthyear}")
    public ResponseEntity<List<UserDTO>> userInfo(@PathVariable("birthyear")Integer birthyear){
        List<UserDTO> userList = this.userService.getUserInfoByBirth(birthyear);
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value = "addr-birthyear")
    public ResponseEntity<List<UserDTO>> userInfo(@RequestParam String addr,@RequestParam Integer birthyear){
        List<UserDTO> userList = this.userService.getUserInfoByAandB(addr,birthyear);
        return ResponseEntity.ok(userList);
    }
}

