package com.example.poseexam.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authentbl")
public class AuthenEntity {
    @Id
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name="role", length = 20)
    private String role;

    @Column(name = "writer", length = 20)
    private String writer;

}