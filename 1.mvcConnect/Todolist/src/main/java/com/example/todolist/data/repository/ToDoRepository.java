package com.example.todolist.data.repository;

import com.example.todolist.data.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Integer> {
    public int deleteByCompletedTrue();
}
