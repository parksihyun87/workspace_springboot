package com.example.jpa_exam.controller;
import com.example.jpa_exam.data.dto.UserDTO;
import com.example.jpa_exam.data.entity.UserEntity;
import com.example.jpa_exam.data.repository.UserEntityRepository;
import com.example.jpa_exam.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/userinfo")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/user-list")
    public ResponseEntity<List<UserDTO>> getAllUserList(){
        List<UserDTO> userList =this.userService.getAllUserList();
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value = "/addr/{addr}")
    public ResponseEntity<List<UserDTO>> userInfo(@PathVariable("addr") String addr){
        List<UserDTO> userList = this.userService.getUserInfoByAddr(addr);
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value="/birthyear/{birthyear}")
    public ResponseEntity<List<UserDTO>> userInfo(@PathVariable("birthyear")Integer birthyear){
        List<UserDTO> userList = this.userService.getUserInfoByBirth(birthyear);
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value = "/addr-birthyear")
    public ResponseEntity<List<UserDTO>> userInfo(@RequestParam String addr,@RequestParam Integer birthyear){
        List<UserDTO> userList = this.userService.getUserInfoByAandB(addr,birthyear);
        return ResponseEntity.ok(userList);
    }

    @PostMapping(value="/adduser")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO user = this.userService.addUserInfo(userDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value="/userid/{userid}")
    public ResponseEntity<Boolean> getDupliUserById(@PathVariable("userid") String userid){
        Boolean isDuplicate = this.userService.getDupliUserById(userid);
        return ResponseEntity.ok(isDuplicate);
    }
}

