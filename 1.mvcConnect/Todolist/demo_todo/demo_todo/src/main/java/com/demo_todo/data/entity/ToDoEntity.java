package com.demo_todo.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "todotbl")
public class ToDoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private boolean completed;
    private LocalDateTime completedDate;
    private LocalDateTime createdDate;
}
