package com.demo_todo.controller;

import com.demo_todo.data.dto.ToDoDTO;
import com.demo_todo.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ToDoController {
    private  final ToDoService toDoService;

    @GetMapping(value="/todo-list")
    public ResponseEntity<List<ToDoDTO>> getToDoList(){
        List<ToDoDTO> toDoDTOList=this.toDoService.getToDoList();
        return ResponseEntity.status(HttpStatus.OK).body(toDoDTOList);
    }

    @PostMapping(value="/todo")
    public ResponseEntity<ToDoDTO> addToDo(@RequestBody ToDoDTO toDoDTO){
        ToDoDTO addToDoDTO=this.toDoService.addToDo(toDoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(addToDoDTO);
    }

    @PutMapping(value="/todo/{id}")
    public ResponseEntity<ToDoDTO> completeToDo(@PathVariable("id") Integer id){
        ToDoDTO toDoDTO=this.toDoService.completeToDo(id);
        if(toDoDTO!=null){
            return ResponseEntity.status(HttpStatus.OK).body(toDoDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(value="/completed-todo")
    public ResponseEntity<String> deleteCompletedToDoList(){
        this.toDoService.deleteCompletedToDoList();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
