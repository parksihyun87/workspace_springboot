package com.example.todolist.data.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoDTO {
    @Min(value=0,message="id는 0 이상입니다.")
    Integer id;
    String task;
    String description;
    private boolean completed;
}
