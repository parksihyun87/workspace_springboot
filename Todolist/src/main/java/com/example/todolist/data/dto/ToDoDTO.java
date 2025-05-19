package com.example.todolist.data.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoDTO {
    Integer id;
    String task;
    String description;
    private boolean completed;
}
