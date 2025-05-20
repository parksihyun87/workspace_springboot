package com.demo_todo.data.repository;

import com.demo_todo.data.entity.ToDoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoEntity, Integer> {
    public void deleteByCompletedTrue();
}
