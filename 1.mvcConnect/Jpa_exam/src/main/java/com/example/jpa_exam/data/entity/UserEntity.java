package com.example.jpa_exam.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "usertbl")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @Column(name = "userid", nullable = false, length = 8)
    private String userid;

    @Column(name = "username", nullable = false, length = 10)
    private String username;

    @Column(name = "birthyear", nullable = false)
    private Integer birthyear;

    @Column(name = "addr", nullable = false, length = 2)
    private String addr;

    @Column(name = "mobile1", length = 3)
    private String mobile1;

    @Column(name = "mobile2", length = 8)
    private String mobile2;

    @Column(name = "height")
    private Short height;

    @Column(name = "mdate")
    private LocalDate mdate;

}