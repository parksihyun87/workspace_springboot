package com.example.todolist.service;

import com.example.todolist.data.dao.ToDoDAO;
import com.example.todolist.data.dto.ToDoDTO;
import com.example.todolist.data.entity.ToDo;
import com.example.todolist.exception.MyException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoDAO toDoDAO;

    public List<ToDoDTO> getAllToDoList(){
        // 처음에 받을때는 entity로 받아서 dao로 반환
        List<ToDoDTO> toDoDTOList = new ArrayList<>();
        List<ToDo> toDoLists =this.toDoDAO.getAllToDoList();
        for (ToDo toDo : toDoLists){
            ToDoDTO toDoDTO = ToDoDTO.builder()
                    .id(toDo.getId())
                    .task(toDo.getTask())
                    .build();
            toDoDTOList.add(toDoDTO);
        }
        return toDoDTOList;
    }

    public ToDoDTO addToDoList(ToDoDTO toDoDTO){
        if(toDoDTO.getTask().contains("달리기")){
            throw new MyException("안됩니다.");
        }
        ToDo addToDoList= toDoDAO.addToDoList(toDoDTO.getTask(),
                LocalDateTime.now(),"생성");
        ToDoDTO saveToDoDTO = ToDoDTO.builder()
                    .id(addToDoList.getId())
                    .task(addToDoList.getTask())
                    .build();
        return saveToDoDTO;
    }

   public ToDoDTO updateToDoList(ToDoDTO toDoDTO){
        ToDo updateToDolist = this.toDoDAO.updateToDoList(toDoDTO.getId(),toDoDTO.getDescription(),LocalDateTime.now());
        if(updateToDolist==null){
            throw new MyException("수정상품이 미존재합니다.");
        }
        ToDoDTO saveUpdateDto = ToDoDTO.builder()
                .id(updateToDolist.getId())
                .task(updateToDolist.getTask())
                .description(updateToDolist.getDescription())
                .build();
        return saveUpdateDto;
   }

    public Boolean deleteToDoList() {
        List<ToDo> toDoList = this.toDoDAO.getAllToDoList();
        boolean isDeleted = false;
        for (ToDo todo : toDoList) {
            if (todo.isCompleted()) {
                boolean deleted = this.toDoDAO.deleteById(todo.getId());
                if (deleted) {
                    isDeleted = true;
                }
            }
        }
        return isDeleted;
    }
}
