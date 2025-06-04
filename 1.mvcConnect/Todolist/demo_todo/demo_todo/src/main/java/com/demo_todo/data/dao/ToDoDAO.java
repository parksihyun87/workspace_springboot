package com.demo_todo.data.dao;

import com.demo_todo.data.entity.ToDoEntity;
import com.demo_todo.data.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoDAO {
    private final ToDoRepository toDoRepository;

    public List<ToDoEntity> getToDoList(){
        return this.toDoRepository.findAll();
    }

    public ToDoEntity getToDo(Integer id){
        Optional<ToDoEntity> toDoEntityOptional=this.toDoRepository.findById(id);
        if(toDoEntityOptional.isPresent()){
            return toDoEntityOptional.get();
        }
        return null;
    }

    public ToDoEntity addToDo(String title){
        ToDoEntity toDoEntity=ToDoEntity.builder()
                .title(title)
                .completed(false)
                .createdDate(LocalDateTime.now())
                .build();
        ToDoEntity saveTodo = this.toDoRepository.save(toDoEntity);
        return saveTodo;
    }

    public ToDoEntity completeToDo(Integer id){
      Optional<ToDoEntity> updateToDoOptional=this.toDoRepository.findById(id);
      if(updateToDoOptional.isPresent()){
          ToDoEntity updateToDoEntity=updateToDoOptional.get();
          updateToDoEntity.setCompleted(true);
          updateToDoEntity.setCompletedDate(LocalDateTime.now());
          ToDoEntity updatedToDoEntity =this.toDoRepository.save(updateToDoEntity);
          return updatedToDoEntity;
      }
      return null;
    }

    public void deleteCompletedToDoList(){
        this.toDoRepository.deleteByCompletedTrue();
    }

    public boolean deleteToDo(Integer id){
        if(this.toDoRepository.existsById(id)){
            this.toDoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
