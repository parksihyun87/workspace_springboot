package com.example.todolist.service;

import com.example.todolist.data.dao.ToDoDAO;
import com.example.todolist.data.dto.ToDoDTO;
import com.example.todolist.data.entity.ToDo;
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
        // 여기는 dao의 entity 자료형 반환값을 받아서 dto로 변환이므로 내부는 값을 쓰기만 하면 됨
        List<ToDoDTO> toDoDTOList = new ArrayList<>();// 리스트와 어레이리스트 모르겠음
        List<ToDo> toDoLists =this.toDoDAO.getAllToDoList();
        for (ToDo toDo : toDoLists){
            ToDoDTO toDoDTO = ToDoDTO.builder() // 빌더는 생성할 클래스 자체에 붙이고 변수로 받아야 함.
                    .id(toDo.getId())
                    .task(toDo.getTask())
                    .build();
            toDoDTOList.add(toDoDTO);
        }
        return toDoDTOList;
    }

    public ToDoDTO addToDoList(ToDoDTO toDoDTO){
        // toDoDTO를 어떻게 할 것인가.
        // 앞으로 계속 보내도 쓸 수 가 없음. 해체해서 entity형태로 전달.
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
        ToDoDTO saveUpdateDto = ToDoDTO.builder()
                .id(updateToDolist.getId())
                .task(updateToDolist.getTask())
                .description(updateToDolist.getDescription())
                .build();
        return saveUpdateDto;
   }
}
