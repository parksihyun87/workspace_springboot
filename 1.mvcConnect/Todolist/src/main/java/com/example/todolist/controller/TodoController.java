package com.example.todolist.controller;

import com.example.todolist.data.dto.ToDoDTO;
import com.example.todolist.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final ToDoService toDoService;

    // 불러오기
    @GetMapping(value = "/todolist")
    public ResponseEntity<List<ToDoDTO>> getAllToDoList(){
        List<ToDoDTO> toDoDTOList = this.toDoService.getAllToDoList();
        return ResponseEntity.ok(toDoDTOList);
    }
    //p해서 바디에 정보를 넣어서 프론트에서 준다.
    //포스트에서는 쓰는 정보만
    // update는 (id,쓰는정보), delete는 (id만)

    // 담아가지고 끝까지 올라간담에, 마지막 부분에서 텍스트만 타스크에 클래스형태로 세이브.
    // db방향으로 호출하면서 실행하는 부분때문에 db에 영향이 가고, 반대로 그쪽에서 그에 관한
    // 리턴값을 계속 전해주면서 필요한 데이터가 다시 프론트엔드와의 접촉점 부분으로 오게 된다.

    @PostMapping(value="/addtodolist")
    public ResponseEntity<ToDoDTO> addToDoList(@RequestBody ToDoDTO toDoDTO ){
        ToDoDTO addDTO=this.toDoService.addToDoList(toDoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addDTO);
    }

    @PutMapping(value="/updatetodolist")
    public ResponseEntity<ToDoDTO> updateToDoList(@Valid @RequestBody ToDoDTO toDoDTO ){
        ToDoDTO updateDTO= this.toDoService.updateToDoList(toDoDTO);
        return ResponseEntity.ok(updateDTO);
    }

    @DeleteMapping(value="/deletetodolist")
    public ResponseEntity<String> deleteToDoList(){
        Boolean result = this.toDoService.deleteToDoList();
        if(result){
            return ResponseEntity.status(HttpStatus.OK).body("상품삭제성공");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제상품검색 실패");
    }
}




