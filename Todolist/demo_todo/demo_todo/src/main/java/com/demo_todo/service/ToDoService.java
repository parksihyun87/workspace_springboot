package com.demo_todo.service;

import com.demo_todo.data.dao.ToDoDAO;
import com.demo_todo.data.dto.ToDoDTO;
import com.demo_todo.data.entity.ToDoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoDAO toDoDAO;

    public List<ToDoDTO> getToDoList(){
        List<ToDoEntity> toDoEntityList=this.toDoDAO.getToDoList();
        List<ToDoDTO> toDoDTOList=new ArrayList<>();
        for(ToDoEntity toDo: toDoEntityList){
            ToDoDTO toDoDTO=ToDoDTO.builder()
                    .id(toDo.getId())
                    .title(toDo.getTitle())
                    .completed(toDo.isCompleted())
                    .build();
            toDoDTOList.add(toDoDTO);
        }

        return toDoDTOList;
    }

    public ToDoDTO addToDo(ToDoDTO toDoDTO){
        ToDoEntity saveToDoEntity=this.toDoDAO.addToDo(toDoDTO.getTitle());
        ToDoDTO addToDoDTO=ToDoDTO.builder()
                .id(saveToDoEntity.getId())
                .title(saveToDoEntity.getTitle())
                .completed(saveToDoEntity.isCompleted())
                .build();
        return addToDoDTO;
    }

    public ToDoDTO completeToDo(Integer id){
        ToDoEntity toDoEntity=this.toDoDAO.completeToDo(id);
        if(toDoEntity!=null){
            ToDoDTO toDoDTO=ToDoDTO.builder()
                    .id(toDoEntity.getId())
                    .title(toDoEntity.getTitle())
                    .completed(toDoEntity.isCompleted())
                    .build();
            return toDoDTO;
        }
        return null;
    }

    public void deleteCompletedToDoList(){
        List<ToDoEntity> toDoEntityList=this.toDoDAO.getToDoList();
        for(ToDoEntity todo:toDoEntityList){
            if(todo.isCompleted()){
                this.toDoDAO.deleteToDo(todo.getId());
            }
        }
//        this.toDoDAO.deleteCompletedToDoList();

    }

}
