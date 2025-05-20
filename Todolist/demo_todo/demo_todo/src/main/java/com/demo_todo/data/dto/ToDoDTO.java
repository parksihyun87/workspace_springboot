package com.demo_todo.data.dto;

import lombok.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ToDoDTO {
    private Integer id;
    private String title;
    private boolean completed;
}
