package com.example.todolist.data.dao;

import com.example.todolist.data.dto.ToDoDTO;
import com.example.todolist.data.entity.ToDo;
import com.example.todolist.data.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// 파이널 해서 리콰이어드 해야 주입성을 가진 동시에, 스프링 부트화적인 의미와 관리에 속한 객체를 쓸 수 있다.
public class
ToDoDAO {
    public final ToDoRepository toDoRepository;

    public List<ToDo> getAllToDoList(){
        List<ToDo> toDoList = this.toDoRepository.findAll();
        return  toDoList;
    }

    public ToDo addToDoList(String task,LocalDateTime created,String description){
        ToDo toDo= ToDo.builder()
                .task(task)
                .created(created)
                .description(description)
                .build();
        return this.toDoRepository.save(toDo);
    }

    public ToDo updateToDoList(int id,String description,LocalDateTime created){
        Optional<ToDo> updateToDo = this.toDoRepository.findById(id);
        if(updateToDo.isPresent()){
            ToDo toDo = updateToDo.get();
            toDo.setDescription(description);
            toDo.setCreated(created);
            toDo.setCompleted(true);
            return this.toDoRepository.save(toDo);
        }
        return null;
    }
}
